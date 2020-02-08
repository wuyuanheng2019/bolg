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
     * 查询文章分类(father - son )
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

    /**
     * 通过id 更新当前节点以及父节点下的 num
     *
     * @param categoryId 当前节点 id
     * @param num        节点下的文章
     */
    void updateCategoryNumById(Long categoryId, int num);

    /**
     * 通过文章id查询当前分类
     *
     * @param id 文章id
     * @return 当前分类
     */
    CategoryInfo getCategoryInfoById(Long id);

    /**
     * 自增 或 自减
     *
     * @param categoryId 分类id
     * @param number     数值
     */
    void updateNumber(Long categoryId, int number);

    /**
     * 删除当前分类
     *
     * @param id 分类id
     * @return 是否成功
     */
    boolean deleteById(long id);
}
