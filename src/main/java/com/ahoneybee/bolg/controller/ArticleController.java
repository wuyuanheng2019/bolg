package com.ahoneybee.bolg.controller;


import com.ahoneybee.bolg.entity.ArticleContent;
import com.ahoneybee.bolg.entity.ArticleInfo;
import com.ahoneybee.bolg.entity.vo.ArticleInfoCategoryVo;
import com.ahoneybee.bolg.service.IArticleContentService;
import com.ahoneybee.bolg.service.IArticleInfoService;
import com.ahoneybee.bolg.util.MyPages;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章分类关联表 前端控制器
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/article")
@Api(value = "ArticleController", tags = {"文章接口文档"})
public class ArticleController {

    /**
     * 文章信息service
     */
    private final IArticleInfoService articleInfoService;

    /**
     * 文章内容service
     */
    private final IArticleContentService articleContentService;

    public ArticleController(IArticleInfoService articleInfoService, IArticleContentService articleContentService) {
        this.articleInfoService = articleInfoService;
        this.articleContentService = articleContentService;
    }


    @GetMapping("/any/byTime")
    @ApiOperation(value = "查询文章列表", notes = "查询文章列表")
    public PageInfo<ArticleInfoCategoryVo> listArticleInfoByTime(MyPages myPages) {
        return articleInfoService.listArticleInfo(myPages);
    }


    @GetMapping("/any/article")
    @ApiModelProperty(value = "id", example = "1", required = true)
    @ApiOperation(value = "查询文章详情页", notes = "查询文章详情页")
    public List<Object> getArticle(Long id) {
        return articleInfoService.getArticle(id);
    }


    @GetMapping("/any/byId")
    @ApiModelProperty(value = "id", example = "1", required = true)
    @ApiOperation(value = "查询文章信息(单条)", notes = "查询文章信息(单条)")
    public ArticleInfo getArticleInfoById(Long id) {
        return articleInfoService.getById(id);
    }


    @GetMapping("/any/contentById")
    @ApiModelProperty(value = "id", example = "1", required = true)
    @ApiOperation(value = "查询文章内容", notes = "查询文章内容")
    public ArticleContent getArticleContentById(Long id) {
        return articleContentService.getByArticleId(id);
    }


    @GetMapping("/any/{categoryId}")
    @ApiModelProperty(value = "categoryId", example = "1", required = true)
    @ApiOperation(value = "通过分类id查找文章", notes = "通过分类id查找文章")
    public PageInfo<ArticleInfoCategoryVo> listArticleInfoByCategory(@PathVariable Long categoryId, MyPages pages) {
        return articleInfoService.listArticleInfoByCategory(categoryId, pages);
    }


    @PostMapping("/admin/postArticle")
    @ApiModelProperty(value = "map", required = true)
    @ApiOperation(value = "保存文章信息", notes = "保存文章信息")
    public boolean postArticle(@RequestBody Map<String, Object> map) {
        return articleInfoService.postArticle(map);
    }


    @PutMapping("/admin/updateArticle")
    @ApiModelProperty(value = "map", required = true)
    @ApiOperation(value = "更新文章信息", notes = "更新文章信息")
    public boolean updateArticleInfo(@RequestBody Map<String, Object> map) {
        return articleInfoService.updateArticle(map);
    }

}
