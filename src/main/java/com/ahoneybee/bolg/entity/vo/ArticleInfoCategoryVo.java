package com.ahoneybee.bolg.entity.vo;

import com.ahoneybee.bolg.entity.ArticleCategory;
import com.ahoneybee.bolg.entity.ArticleContent;
import com.ahoneybee.bolg.entity.ArticleInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 文章信息与分类关联对象
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleInfoCategoryVo implements Serializable {

    /**
     * 文章信息
     */
    private ArticleInfo articleInfo;

    /**
     * 分类(集合)
     */
    private List<String> articleCategoryNames;

    /**
     * 文章内容
     */
    private ArticleContent articleContent;


    /**
     * 文章分类关系
     */
    private ArticleCategory articleCategory;

}
