package com.ahoneybee.bolg.service;

import com.ahoneybee.bolg.entity.CategoryInfo;
import com.ahoneybee.bolg.util.MyPages;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 分类信息表 服务类
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
public interface ICategoryInfoService extends IService<CategoryInfo> {


    /**
     * 查询文章分类
     *
     * @param articleId 文章id
     * @return 结果集
     */
    List<String> listCategoryByArticleId(Long articleId);

    /**
     * 查询id下的子类
     *
     * @param categoryId 当前分类id
     * @return 分类ids
     */
    List<Long> findSon(Long categoryId);

}
