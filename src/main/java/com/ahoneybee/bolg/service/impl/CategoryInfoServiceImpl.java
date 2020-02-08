package com.ahoneybee.bolg.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.ahoneybee.bolg.entity.CategoryInfo;
import com.ahoneybee.bolg.mapper.CategoryInfoMapper;
import com.ahoneybee.bolg.service.ICategoryInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        List<Long> list = Collections.synchronizedList(new ArrayList<>());
        list.add(categoryId);
        return findInfoDown(categoryId, list);
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

    /**
     * 递归调用 所有子集合的id(向下调用)
     *
     * @param categoryId 当前分类id
     * @param list       容器(存放找到的 categoryId )
     * @return 分类ids
     */
    private List<Long> findInfoDown(Long categoryId, List<Long> list) {

        //找到子节点
        List<CategoryInfo> categoryInfoList = lambdaQuery().in(CategoryInfo::getParentId, categoryId).list();

        if (CollectionUtil.isNotEmpty(categoryInfoList)) {

            //一对多的映射关系，多线程调用
            categoryInfoList.parallelStream().forEach(
                    categoryInfo -> {
                        list.add(categoryInfo.getId());
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

            //找到父节点并田间操作
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
