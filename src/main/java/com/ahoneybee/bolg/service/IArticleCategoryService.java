package com.ahoneybee.bolg.service;

import com.ahoneybee.bolg.entity.ArticleCategory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文章分类关联表 服务类
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
public interface IArticleCategoryService extends IService<ArticleCategory> {

    /**
     * 通过文章id 查找结果集
     *
     * @param id 文章id
     * @return 文章分类关系
     */
    ArticleCategory getCategoryByArticleId(Long id);

    /**
     * 更新当前文章分类
     *
     * @param articleCategory 当前分类信息
     */
    void updateByArticleId(ArticleCategory articleCategory);
}
