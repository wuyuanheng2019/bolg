package com.ahoneybee.bolg.service;

import com.ahoneybee.bolg.entity.ArticleContent;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文章内容 服务类
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
public interface IArticleContentService extends IService<ArticleContent> {


    /**
     * 根据文章id查询内容
     *
     * @param articleId 文章id
     * @return 内容
     */
    ArticleContent getByArticleId(long articleId);
}
