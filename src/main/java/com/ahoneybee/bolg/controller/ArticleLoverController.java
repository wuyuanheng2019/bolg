package com.ahoneybee.bolg.controller;

import com.ahoneybee.bolg.service.IArticleInfoService;
import com.ahoneybee.bolg.service.IArticleLoverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@RequestMapping("/articleLove")
@Api(value = "ArticleLoverController", tags = {"文章点赞接口文档"})
public class ArticleLoverController {

    /**
     * 文章点赞service
     */
    private final IArticleLoverService articleLoverService;

    /**
     * 文章内容service
     */
    private final IArticleInfoService articleInfoService;

    public ArticleLoverController(IArticleLoverService articleLoverService, IArticleInfoService articleInfoService) {
        this.articleLoverService = articleLoverService;
        this.articleInfoService = articleInfoService;
    }

    @PostMapping("/user/postLover")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "aid", value = "文章id", required = true),
            @ApiImplicitParam(name = "request", value = "请求"),
            @ApiImplicitParam(name = "flag", value = "点赞标志", required = true)
    })
    @ApiOperation(value = "点赞 or 取消", notes = "点赞 or 取消")
    public boolean postArticleLover(long aid, HttpServletRequest request, boolean flag) {
        return articleInfoService.updateArticleInfo(aid, request.getHeader("X-Real-Ip"), flag);
    }


    @GetMapping("/user/getLoveTrueOrFalse")
    @ApiImplicitParam(name = "aid", value = "文章id", required = true)
    @ApiOperation(value = "查看当前用户是否点赞", notes = "查看当前用户是否点赞")
    public long getLoveTrueOrFalse(HttpServletRequest request, long aid) {
        return articleLoverService.getLoveTrueOrFalse(request.getHeader("X-Real-Ip"), aid);
    }
}
