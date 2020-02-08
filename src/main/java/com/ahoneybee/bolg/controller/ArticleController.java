package com.ahoneybee.bolg.controller;


import com.ahoneybee.bolg.entity.ArticleInfo;
import com.ahoneybee.bolg.entity.vo.ArticleInfoCategoryVo;
import com.ahoneybee.bolg.service.IArticleInfoService;
import com.ahoneybee.bolg.util.MyPages;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
     * 文章内容service
     */
    private final IArticleInfoService articleInfoService;

    public ArticleController(IArticleInfoService articleInfoService) {
        this.articleInfoService = articleInfoService;
    }


    @GetMapping("/any/byTime")
    @ApiOperation(value = "查询文章列表", notes = "查询文章列表")
    public PageInfo<ArticleInfoCategoryVo> listArticleInfoByTime(MyPages myPages) {
        return articleInfoService.listArticleInfo(myPages);
    }


    @GetMapping("/any/article")
    @ApiModelProperty(value = "id", example = "1")
    @ApiOperation(value = "查询文章详情页", notes = "查询文章详情页")
    public List<Object> getArticle(long id) {
        return articleInfoService.getArticle(id);
    }


    @GetMapping("/any/byId")
    @ApiModelProperty(value = "id", example = "1")
    @ApiOperation(value = "查询文章信息(单条)", notes = "查询文章信息(单条)")
    public ArticleInfo getArticleInfoById(long id) {
        return articleInfoService.getById(id);
    }


    @GetMapping("/any/{categoryId}")
    @ApiModelProperty(value = "categoryId", example = "1", required = true)
    @ApiOperation(value = "通过分类id查找文章", notes = "通过分类id查找文章")
    public PageInfo<ArticleInfoCategoryVo> listArticleInfoByCategory(@PathVariable long categoryId, MyPages pages) {
        return articleInfoService.listArticleInfoByCategory(categoryId, pages);
    }


    @PostMapping("/admin/postArticle")
    @ApiModelProperty(value = "map", required = true)
    @ApiOperation(value = "保存", notes = "保存")
    public boolean postArticle(@RequestBody Map<String, Object> map) {
        return articleInfoService.postArticle(map);
    }
}
