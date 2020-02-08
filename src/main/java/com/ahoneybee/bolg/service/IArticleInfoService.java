package com.ahoneybee.bolg.service;

import com.ahoneybee.bolg.entity.ArticleInfo;
import com.ahoneybee.bolg.entity.vo.ArticleInfoCategoryVo;
import com.ahoneybee.bolg.util.MyPages;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章信息 服务类
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
public interface IArticleInfoService extends IService<ArticleInfo> {


    /**
     * 分页查询(更具当前创建时间降序)
     *
     * @param myPages 分页查询条件
     * @return 结果集
     */
    List<ArticleInfo> listArticleInfoByTime(MyPages myPages);

    /**
     * 文章列表
     * 1 得到文章结果集
     * 2 通过文章查询文章分类
     * 3 封装
     *
     * @param myPages 分页查询条件
     * @return 结果集
     */
    PageInfo<ArticleInfoCategoryVo> listArticleInfo(MyPages myPages);

    /**
     * 文章详细信息(点赞，评论，内容等)
     *
     * @param articleId 文章id
     * @return 结果集
     */
    List<Object> getArticle(long articleId);

    /**
     * 访问量自增
     *
     * @param articleInfo 文章信息
     */
    void saveTraffic(ArticleInfo articleInfo);

    /**
     * 点赞
     *
     * @param aid  文章id
     * @param ip   用户ip
     * @param flag 标识(是否点赞)
     * @return 是否成功
     */
    boolean updateArticleInfo(long aid, String ip, boolean flag);

    /**
     * 查询当前分类下的文章列表
     *
     * @param categoryId 分类id
     * @param pages      分页条件
     * @return 结果集
     */
    PageInfo<ArticleInfoCategoryVo> listArticleInfoByCategory(long categoryId, MyPages pages);

    /**
     * 保存文章(及其信息等)
     *
     * @param map 两个元素，1 文章信息，2 文章内容，3 文章和分类间的list映射
     * @return 成功为true，失败为false(是否成功保存)
     */
    boolean postArticle(Map<String, Object> map);
}
