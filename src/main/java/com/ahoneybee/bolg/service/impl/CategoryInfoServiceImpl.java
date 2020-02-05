package com.ahoneybee.bolg.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.ahoneybee.bolg.entity.CategoryInfo;
import com.ahoneybee.bolg.entity.vo.ArticleInfoCategoryVo;
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
    public List<String> listCategoryByArticleId(long articleId) {

        /*
         * 1 文章分类表设计为递归结构(pid -> id)
         * 2 通过文章id查找到分类信息，文章分类是一对一的关联关系
         * 3 通过分类信息找到父类信息
         * 4 整合并进行封装
         */
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        CategoryInfo categoryInfo = baseMapper.listCategoryByArticleId(articleId);
        return findInfo(categoryInfo, list);
    }

    /**
     * 递归调用 查找所有类型
     *
     * @param categoryInfo 分类信息
     * @param list         分类名称集合
     * @return 结果集
     */
    private List<String> findInfo(CategoryInfo categoryInfo, List<String> list) {

        if (categoryInfo != null) {
            list.add(
                    lambdaQuery()
                            .eq(CategoryInfo::getId, categoryInfo.getId())
                            .one().getName()
            );

            categoryInfo = getById(categoryInfo.getParentId());
            findInfo(categoryInfo, list);
        }
        return CollectionUtil.reverse(list);
    }
}
