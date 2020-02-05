package com.ahoneybee.bolg.service;

import com.ahoneybee.bolg.entity.CommentInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 评论详情表 服务类
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
public interface ICommentInfoService extends IService<CommentInfo> {

    /**
     * 通过文章id查询评论详情
     *
     * @param articleId 文章id
     * @return 评论信息
     */
    List<CommentInfo> listInfoByArticleId(long articleId);
}
