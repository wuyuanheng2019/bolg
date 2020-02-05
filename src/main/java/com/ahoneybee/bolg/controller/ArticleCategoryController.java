package com.ahoneybee.bolg.controller;


import com.ahoneybee.bolg.entity.vo.ArticleInfoCategoryVo;
import com.ahoneybee.bolg.service.IArticleInfoService;
import com.ahoneybee.bolg.util.MyPages;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 文章分类关联表 前端控制器
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@Slf4j
@RestController
@Api(value = "ArticleCategoryController", tags = {"文章分类接口文档"})
@RequestMapping("/article-category")
public class ArticleCategoryController {

    private final IArticleInfoService articleInfoService;

    public ArticleCategoryController(IArticleInfoService articleInfoService) {
        this.articleInfoService = articleInfoService;
    }


    @ApiOperation(value = "分页查询文章列表", notes = "分页查询文章列表")
    @GetMapping("/any/byTime")
    public PageInfo<ArticleInfoCategoryVo> listArticleInfoByTime(MyPages myPages) {
        return articleInfoService.listArticleInfo(myPages);
    }


    @GetMapping("/any/article")
    public List<Object> getArticle(long id) {
        return articleInfoService.getArticle(id);
    }

}
