package com.ahoneybee.bolg.controller;


import com.ahoneybee.bolg.entity.CategoryInfo;
import com.ahoneybee.bolg.entity.Node;
import com.ahoneybee.bolg.service.ICategoryInfoService;
import com.ahoneybee.bolg.util.TreeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 分类信息表 前端控制器
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@RestController
@RequestMapping("/categoryInfo")
@Api(value = "CategoryInfoController", tags = {"分类信息接口文档"})
public class CategoryInfoController {


    /**
     * 分类信息service
     */
    private final ICategoryInfoService categoryInfoService;

    public CategoryInfoController(ICategoryInfoService categoryInfoService) {
        this.categoryInfoService = categoryInfoService;
    }


    @GetMapping("/any/list")
    @ApiOperation(value = "分类列表", notes = "分类列表")
    public Node listCategoryInfo() {
        return TreeUtils.buildTree(null, categoryInfoService.list(), 0);
    }


    @GetMapping("/any/listPa")
    @ApiOperation(value = "分类列表(添加文章页面)", notes = "分类列表(添加文章页面)")
    public List<CategoryInfo> listSubCategoryInfo() {
        return categoryInfoService.list();
    }


    @GetMapping("/any/getCategoryInfoById")
    @ApiModelProperty(value = "id", example = "1", required = true)
    @ApiOperation(value = "查询文章当前分类", notes = "查询文章当前分类")
    public CategoryInfo getCategoryInfoById(Long id) {
        return categoryInfoService.getCategoryInfoById(id);
    }


    @DeleteMapping("/admin/{id}")
    @ApiModelProperty(value = "id", example = "1", required = true)
    @ApiOperation(value = "查询文章当前分类", notes = "查询文章当前分类")
    public boolean deleteCategoryInfoById(@PathVariable long id) {
        return categoryInfoService.deleteById(id);
    }

}
