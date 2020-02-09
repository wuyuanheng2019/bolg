package com.ahoneybee.bolg.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.ahoneybee.bolg.entity.ArticleCategory;
import com.ahoneybee.bolg.entity.CategoryInfo;
import com.ahoneybee.bolg.mapper.CategoryInfoMapper;
import com.ahoneybee.bolg.service.IArticleCategoryService;
import com.ahoneybee.bolg.service.IArticleInfoService;
import com.ahoneybee.bolg.service.ICategoryInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 分类信息表 服务实现类
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@Service
public class CategoryInfoServiceImpl extends ServiceImpl<CategoryInfoMapper, CategoryInfo> implements ICategoryInfoService {

    /**
     * 文章分类service
     */
    @Autowired
    private IArticleCategoryService articleCategoryService;

    /**
     * 文章信息service
     */
    @Autowired
    private IArticleInfoService articleInfoService;


    @Override
    public List<String> listCategoryByArticleId(Long articleId) {

        /*
         * 1 文章分类表设计为递归结构(pid -> id)
         * 2 通过文章id查找到分类信息，文章分类是一对一的关联关系
         * 3 通过分类信息找到父类信息
         * 4 整合并进行封装
         */
        List<CategoryInfo> list = new ArrayList<>();
        List<String> listStr = Collections.synchronizedList(new ArrayList<>());
        CategoryInfo categoryInfo = baseMapper.listCategoryByArticleId(articleId);

        //存放
        findInfoUp(categoryInfo, list).parallelStream().forEach(info -> {
            listStr.add(info.getName());
        });
        return listStr;
    }

    @Override
    public List<Long> findSon(Long categoryId) {

        /*
         * 1 找到当前节点下是否存在子节点
         * 2 有：继续做便利添加操作，反之则遍历查找
         * 3 返回结果集
         */
        List<CategoryInfo> categoryInfoList = new ArrayList<>();
        List<Long> listLong = Collections.synchronizedList(new ArrayList<>());
        listLong.add(categoryId);
        List<CategoryInfo> infoDown = findInfoDown(categoryId, categoryInfoList);

        //判断是否为 null
        if (CollectionUtil.isNotEmpty(infoDown)) {
            infoDown.parallelStream().forEach(info -> {
                listLong.add(info.getId());
            });
        }
        return listLong;
    }

    @Override
    public void updateCategoryNumById(Long categoryId, int num) {

        List<CategoryInfo> list = new ArrayList<>();
        CategoryInfo categoryInfo = baseMapper.listCategoryByArticleId(categoryId);

        //更新节点下的文章数量
        findInfoUp(categoryInfo, list).forEach(info -> {
            lambdaUpdate()
                    .eq(CategoryInfo::getId, info.getId())
                    .set(CategoryInfo::getNumber, info.getNumber() + num)
                    .update();
        });
    }

    @Override
    public CategoryInfo getCategoryInfoById(Long id) {

        //查找中间关系
        ArticleCategory articleCategory = articleCategoryService.getCategoryByArticleId(id);
        return getById(articleCategory.getCategoryId());
    }


    @Override
    public void updateNumber(Long categoryId, int number) {

        //得到当前分类
        List<CategoryInfo> categoryInfoList = new ArrayList<>();
        CategoryInfo categoryInfo = getById(categoryId);

        //分类下的数量操作
        findInfoUp(categoryInfo, categoryInfoList).forEach(
                info -> {
                    info.setNumber(info.getNumber() + number);
                    updateById(info);
                }
        );
    }

    @Override
    public boolean deleteById(Long id) {

        /*
         * 1 找到当前节点的子节点集合
         * 2 找到当前分类下的文章，并统计数量
         * 3 删除子节点集合 以及 之下的文章等相关内容
         */
        List<CategoryInfo> categoryInfoList = new ArrayList<>();
        List<CategoryInfo> infos = findInfoDown(id, categoryInfoList);

        //删除文章
        if (CollectionUtil.isNotEmpty(infos)) {
            infos.forEach(
                    categoryInfo -> {

                        //查找文章分类关联
                        List<ArticleCategory> categories = articleCategoryService.lambdaQuery()
                                .eq(ArticleCategory::getCategoryId, categoryInfo.getId())
                                .list();

                        //delete
                        if (CollectionUtil.isNotEmpty(categories)) {
                            categories.forEach(
                                    articleCategory -> {
                                        articleInfoService.deleteArticle(articleCategory.getArticleId());
                                    }
                            );
                        }

                    }
            );
        }

        //删除分类
        lambdaUpdate().eq(CategoryInfo::getId, infos).remove();
        return true;
    }

    @Override
    public void updateByArticleId(ArticleCategory articleCategory) {

        /*
         *  1 查看当前分类是否改变
         *  2 如改变，则进行修改，反之则不动
         */
        ArticleCategory one = articleCategoryService.getCategoryByArticleId(articleCategory.getArticleId());
        if (!articleCategory.getCategoryId().equals(one.getCategoryId())) {

            //当前分类 +1，之前分类 -1
            updateNumber(articleCategory.getCategoryId(), 1);
            updateNumber(one.getCategoryId(), -1);
        }
    }


    /**
     * 递归调用 所有子集合的id(向下调用)
     *
     * @param categoryId 当前分类id
     * @param list       容器(存放找到的 categoryId )
     * @return 分类ids
     */
    private List<CategoryInfo> findInfoDown(Long categoryId, List<CategoryInfo> list) {

        //找到子节点
        List<CategoryInfo> categoryInfoList = lambdaQuery().in(CategoryInfo::getParentId, categoryId).list();

        if (CollectionUtil.isNotEmpty(categoryInfoList)) {

            //一对多的映射关系，多线程调用
            categoryInfoList.forEach(
                    categoryInfo -> {
                        list.add(categoryInfo);
                        findInfoDown(categoryInfo.getId(), list);
                    }
            );
        }
        return list;
    }

    /**
     * 递归调用 查找所有类型 (向上调用)
     *
     * @param categoryInfo 分类信息
     * @param list         容器(存放找到的 categoryInfo )
     * @return 分类名称集合
     */
    private List<CategoryInfo> findInfoUp(CategoryInfo categoryInfo, List<CategoryInfo> list) {

        if (categoryInfo != null) {

            //找到父节点并进行操作
            list.add(lambdaQuery()
                    .select(CategoryInfo::getId)
                    .select(CategoryInfo::getName)
                    .select(CategoryInfo::getNumber)
                    .eq(CategoryInfo::getId, categoryInfo.getId())
                    .one()
            );

            categoryInfo = getById(categoryInfo.getParentId());
            findInfoUp(categoryInfo, list);
        }
        return list;
    }
}
