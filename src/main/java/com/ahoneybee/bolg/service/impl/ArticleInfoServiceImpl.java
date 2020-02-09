package com.ahoneybee.bolg.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.ahoneybee.bolg.entity.ArticleCategory;
import com.ahoneybee.bolg.entity.ArticleComment;
import com.ahoneybee.bolg.entity.ArticleContent;
import com.ahoneybee.bolg.entity.ArticleInfo;
import com.ahoneybee.bolg.entity.vo.ArticleInfoCategoryVo;
import com.ahoneybee.bolg.mapper.ArticleInfoMapper;
import com.ahoneybee.bolg.service.*;
import com.ahoneybee.bolg.util.Ip2Region;
import com.ahoneybee.bolg.util.MyPages;
import com.ahoneybee.bolg.util.TreeUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章信息 服务实现类
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@Service
public class ArticleInfoServiceImpl extends ServiceImpl<ArticleInfoMapper, ArticleInfo> implements IArticleInfoService {

    @Autowired
    private ICommentInfoService commentInfoService;

    @Autowired
    private IArticleContentService articleContentService;

    @Autowired
    private ICategoryInfoService categoryInfoService;

    @Autowired
    private IArticleLoverService articleLoverService;

    @Autowired
    private IArticleCategoryService articleCategoryService;

    @Autowired
    private IArticleCommentService articleCommentService;


    @Override
    public List<ArticleInfo> listArticleInfoByTime(MyPages myPages) {

        //传入当前页，条数
        PageHelper.startPage(myPages.getPage(), myPages.getSize());
        return lambdaQuery().orderByDesc(ArticleInfo::getCreateTime).list();
    }

    @Override
    public PageInfo<ArticleInfoCategoryVo> listArticleInfo(MyPages myPages) {

        //时间降序查询
        PageHelper.startPage(myPages.getPage(), myPages.getSize());
        List<ArticleInfo> infos = listArticleInfoByTime(myPages);
        return getArticleInfoCategoryVo(infos);

    }

    @Override
    public List<Object> getArticle(long articleId) {

        //创建封装对象
        List<Object> list = Collections.synchronizedList(new ArrayList<>());

        //添加文章内容，评论，分类
        list.add(articleContentService.getByArticleId(articleId));
        list.add(TreeUtils.buildTree(commentInfoService.listInfoByArticleId(articleId), null, articleId));
        list.add(categoryInfoService.listCategoryByArticleId(articleId));

        //文章访问量+1
        saveTraffic(getById(articleId));
        return list;
    }

    @Override
    public void saveTraffic(ArticleInfo articleInfo) {

        //访问量自增
        lambdaUpdate()
                .set(ArticleInfo::getTraffic, articleInfo.getTraffic() + 1)
                .eq(ArticleInfo::getId, articleInfo.getId())
                .update();

    }

    @Override
    public boolean updateArticleInfo(long aid, String ip, boolean flag) {

        //nginx配置ip，检验
        Ip2Region.judgeIp(ip);

        //点赞 or 取消点赞
        if (flag) {
            baseMapper.updateArticleLov(aid, 1);
            articleLoverService.insertArticleLover(aid, ip);
        } else {
            baseMapper.updateArticleLov(aid, -1);
            articleLoverService.deleteArticleLover(aid, ip);
        }
        return true;
    }

    @Override
    public PageInfo<ArticleInfoCategoryVo> listArticleInfoByCategory(long categoryId, MyPages pages) {

        /*
         * 检测该分类id下是否存在子节点
         *          1 使用数据库处理 2 使用内存处理
         * 通过分类集合查询关联的文章信息
         * 封装vo，返回前台用户
         */
        List<Long> categoryIds = categoryInfoService.findSon(categoryId);
        PageHelper.startPage(pages.getPage(), pages.getSize());

        //查询文章信息
        List<ArticleInfo> infos = baseMapper.listArticleInfoByCategory(categoryIds);
        return getArticleInfoCategoryVo(infos);

    }

    @Override
    public boolean postArticle(Map<String, Object> map) {

        /*
         * 1 读取前台传入的文章信息
         * 2 保存，并创建文章及其分类关联信息
         * 3 返回信息
         */
        ArticleInfoCategoryVo articleVo = getArticleVo(map);
        if (ObjectUtils.isEmpty(articleVo)) {
            return false;
        }

        //db操作(添加)
        insertArticleAndInfo(articleVo.getArticleInfo(),
                articleVo.getArticleContent(), articleVo.getArticleCategory());
        return true;
    }

    @Override
    public boolean updateArticle(Map<String, Object> map) {

        /*
         * 1 读取前台信息
         * 2 更新 (校验分类是否改变)
         *        如果改变，以前类型及其父类的文章数量 -1 ，反之不变
         * 3 返回
         */
        ArticleInfoCategoryVo articleVo = getArticleVo(map);
        if (ObjectUtils.isEmpty(articleVo)) {
            return false;
        }

        //db操作(更新)
        UpdateArticleAndInfo(articleVo.getArticleInfo(),
                articleVo.getArticleContent(), articleVo.getArticleCategory());
        return true;
    }

    @Override
    public boolean deleteArticle(long articleId) {

        /*
         * 删除文章信息
         *      文章内容
         *      文章评论
         *      文章分类
         */
        removeById(articleId);
        articleContentService
                .lambdaUpdate().eq(ArticleContent::getArticleId, articleId)
                .remove();

        //删除相关评论
        List<ArticleComment> articleComments = articleCommentService
                .lambdaQuery().eq(ArticleComment::getArticleId, articleId).list();
        if (CollectionUtil.isNotEmpty(articleComments)) {
            articleComments.forEach(articleComment -> {
                commentInfoService.removeById(articleComment.getCommentId());
            });

            articleCommentService.removeByIds(articleComments);
        }

        //分类操作
        ArticleCategory category = articleCategoryService.getCategoryByArticleId(articleId);
        categoryInfoService.updateCategoryNumById(category.getCategoryId(), -1);

        return true;
    }

    private void UpdateArticleAndInfo(ArticleInfo articleInfo, ArticleContent articleContent,
                                      ArticleCategory articleCategory) {
        //更新文章以及文章内容
        updateById(articleInfo);
        articleContentService.updateById(articleContent);

        //更新文章分类信息
        categoryInfoService.updateByArticleId(articleCategory);
    }

    /**
     * 添加文章
     *
     * @param articleInfo     文章摘要
     * @param articleContent  文章信息
     * @param articleCategory 分类信息
     */
    private void insertArticleAndInfo(ArticleInfo articleInfo, ArticleContent articleContent,
                                      ArticleCategory articleCategory) {
        //文章
        baseMapper.insertArticleInfo(articleInfo);
        Long id = articleInfo.getId();

        //文章内容，分类
        articleContent.setArticleId(id);
        articleContentService.save(articleContent);
        articleCategory.setArticleId(id);
        articleCategoryService.save(articleCategory);

        //更新分类及其父分类 num++
        categoryInfoService.updateCategoryNumById(articleCategory.getCategoryId(), 1);

    }


    /**
     * 读取前台传入map
     *
     * @param map 封装map
     * @return vo
     */
    private ArticleInfoCategoryVo getArticleVo(Map<String, Object> map) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ArticleInfo articleInfo = objectMapper.readValue(
                    objectMapper.writeValueAsString(map.get("articleInfo")), ArticleInfo.class);

            ArticleContent articleContent = objectMapper.readValue(
                    objectMapper.writeValueAsString(map.get("articleContent")), ArticleContent.class);

            ArticleCategory articleCategory = objectMapper.readValue(
                    objectMapper.writeValueAsString(map.get("articleCategory")), ArticleCategory.class);

            //校验对象是否为 null
            if (ObjectUtils.isEmpty(articleInfo)
                    || ObjectUtils.isEmpty(articleContent)
                    || ObjectUtils.isEmpty(articleCategory)) {
                throw new RuntimeException("前台信息传送缺失!!!");
            }
            return new ArticleInfoCategoryVo(articleInfo, null, articleContent, articleCategory);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 添加分类信息
     *
     * @param infos 文章信息
     * @return 封装vo信息 <father - son>
     */
    private PageInfo<ArticleInfoCategoryVo> getArticleInfoCategoryVo(List<ArticleInfo> infos) {

        //注意并发安全问题
        List<ArticleInfoCategoryVo> voList = Collections.synchronizedList(new ArrayList<>());

        //封装分类信息
        infos.parallelStream()
                .forEach(articleInfo ->
                        voList.add(new ArticleInfoCategoryVo(articleInfo,
                                categoryInfoService.listCategoryByArticleId(articleInfo.getId())
                                , null, null))
                );

        //封装结果集
        return new PageInfo<>(voList);
    }

}
