package com.ahoneybee.bolg.controller;


import com.ahoneybee.bolg.entity.ArticleInfo;
import com.ahoneybee.bolg.service.IArticleInfoService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章信息 前端控制器
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@RestController
@RequestMapping("/article-info")
public class ArticleInfoController {

    private final IArticleInfoService articleInfoService;

    public ArticleInfoController(IArticleInfoService articleInfoService) {
        this.articleInfoService = articleInfoService;
    }

    @GetMapping("/any/byId")
    @ApiModelProperty(value = "id", example = "1")
    @ApiOperation(value = "查询文章信息", notes = "查询文章信息")
    public ArticleInfo getArticleInfoById(long id) {
        return articleInfoService.getById(id);
    }


}
