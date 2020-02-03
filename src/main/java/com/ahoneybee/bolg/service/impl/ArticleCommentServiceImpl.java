package com.ahoneybee.bolg.service.impl;

import com.ahoneybee.bolg.entity.ArticleComment;
import com.ahoneybee.bolg.mapper.ArticleCommentMapper;
import com.ahoneybee.bolg.service.IArticleCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章评论关联表 服务实现类
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements IArticleCommentService {

}
