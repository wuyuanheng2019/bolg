package com.ahoneybee.bolg.service;

import com.ahoneybee.bolg.entity.ArticleInfo;
import com.ahoneybee.bolg.entity.vo.ArticleInfoCategoryVo;
import com.ahoneybee.bolg.util.MyPages;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

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
     * @param id 文章id
     * @return 结果集
     */
    List<Object> getArticle(long id);
}
