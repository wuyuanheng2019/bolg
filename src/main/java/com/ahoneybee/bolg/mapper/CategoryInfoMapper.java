package com.ahoneybee.bolg.mapper;

import com.ahoneybee.bolg.entity.CategoryInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 分类信息表 Mapper 接口
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
public interface CategoryInfoMapper extends BaseMapper<CategoryInfo> {

    /**
     * 通过文章id返回该分类
     *
     * @param id 文章id
     * @return 单个分类
     */
    @Select(value = "SELECT c.* " +
            "FROM category_info c " +
            "JOIN article_category ac on c.id = ac.category_id " +
            "JOIN article_info a on a.id = ac.article_id " +
            "WHERE a.id = #{id}")
    CategoryInfo listCategoryByArticleId(long id);
}
