package com.ahoneybee.bolg.controller;

import com.ahoneybee.bolg.entity.CommentInfo;
import com.ahoneybee.bolg.service.IArticleCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 文章点赞 前端控制器
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-06
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/articleComment")
@Api(value = "ArticleCommentController", tags = {"文章评论接口文档"})
public class ArticleCommentController {

    /**
     * 文章评论service
     */
    private final IArticleCommentService articleCommentService;

    public ArticleCommentController(IArticleCommentService articleCommentService) {
        this.articleCommentService = articleCommentService;
    }

    @PostMapping("/user/postComment/{id}")
    @ApiOperation(value = "文章评论", notes = "文章评论")
    public boolean postArticleComment(@PathVariable long id,
                                      @RequestBody CommentInfo commentInfo, HttpServletRequest request) {
        return articleCommentService.postArticleComment(id, commentInfo, request);
    }

}
