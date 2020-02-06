package com.ahoneybee.bolg.service;

import com.ahoneybee.bolg.entity.ArticleComment;
import com.ahoneybee.bolg.entity.CommentInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 文章评论关联表 服务类
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
public interface IArticleCommentService extends IService<ArticleComment> {

    /**
     * 评论
     *
     * @param id          文章id
     * @param commentInfo 评论信息
     * @param request     请求
     * @return 是否成功
     */
    boolean postArticleComment(long id, CommentInfo commentInfo, HttpServletRequest request);
}
