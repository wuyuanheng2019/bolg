package com.ahoneybee.bolg.service.impl;

import com.ahoneybee.bolg.entity.ArticleInfo;
import com.ahoneybee.bolg.entity.vo.ArticleInfoCategoryVo;
import com.ahoneybee.bolg.mapper.ArticleInfoMapper;
import com.ahoneybee.bolg.service.IArticleContentService;
import com.ahoneybee.bolg.service.IArticleInfoService;
import com.ahoneybee.bolg.service.ICategoryInfoService;
import com.ahoneybee.bolg.service.ICommentInfoService;
import com.ahoneybee.bolg.util.MyPages;
import com.ahoneybee.bolg.util.TreeUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    private final ICommentInfoService commentInfoService;

    private final IArticleContentService articleContentService;

    private final ICategoryInfoService categoryInfoService;

    public ArticleInfoServiceImpl(ICategoryInfoService categoryInfoService,
                                  IArticleContentService articleContentService,
                                  ICommentInfoService commentInfoService) {

        this.categoryInfoService = categoryInfoService;
        this.articleContentService = articleContentService;
        this.commentInfoService = commentInfoService;

    }

    @Override
    public List<ArticleInfo> listArticleInfoByTime(MyPages myPages) {

        //传入当前页，条数
        PageHelper.startPage(myPages.getPage(), myPages.getSize());
        return lambdaQuery().orderByDesc(ArticleInfo::getCreateTime).list();

    }

    @Override
    public PageInfo<ArticleInfoCategoryVo> listArticleInfo(MyPages myPages) {

        //注意并发安全问题
        List<ArticleInfoCategoryVo> voList = Collections.synchronizedList(new ArrayList<>());
        listArticleInfoByTime(myPages).parallelStream()
                .forEach(articleInfo ->
                        voList.add(new ArticleInfoCategoryVo(articleInfo,
                                categoryInfoService.listCategoryByArticleId(articleInfo.getId())))
                );

        //封装结果集
        return new PageInfo<>(voList);
    }

    @Override
    public List<Object> getArticle(long articleId) {

        //创建封装对象
        List<Object> list = Collections.synchronizedList(new ArrayList<>());

        //添加文章内容，评论，分类
        list.add(articleContentService.getByArticleId(articleId));
        list.add(TreeUtils.buildTree(commentInfoService.listInfoByArticleId(articleId), articleId));
        list.add(categoryInfoService.listCategoryByArticleId(articleId));


        //文章访问量+1
        saveTraffic(getById(articleId));

        return list;
    }

    @Override
    public boolean saveTraffic(ArticleInfo articleInfo) {
        return lambdaUpdate()
                .set(ArticleInfo::getTraffic, articleInfo.getTraffic() + 1)
                .eq(ArticleInfo::getId, articleInfo.getId())
                .update();
    }

}
