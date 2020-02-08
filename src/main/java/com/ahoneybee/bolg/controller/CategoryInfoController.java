package com.ahoneybee.bolg.controller;


import com.ahoneybee.bolg.entity.CategoryInfo;
import com.ahoneybee.bolg.entity.Node;
import com.ahoneybee.bolg.service.ICategoryInfoService;
import com.ahoneybee.bolg.util.TreeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiOperation(value = "分类列表(文章添加)", notes = "分类列表(文章添加)")
    public List<CategoryInfo> listSubCategoryInfo() {
        return categoryInfoService.list();
    }


}
