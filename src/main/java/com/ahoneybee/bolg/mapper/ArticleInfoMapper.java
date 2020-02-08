package com.ahoneybee.bolg.mapper;

import com.ahoneybee.bolg.entity.ArticleInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 文章信息 Mapper 接口
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
public interface ArticleInfoMapper extends BaseMapper<ArticleInfo> {

    /**
     * 文章点赞+1
     *
     * @param aid 文章id
     * @param i   自增
     */
    @Update("update article_info set love_num = love_num + #{flag} where id = #{id}")
    void updateArticleLov(long aid, int i);

    /**
     * 按分类，时间降序查询全部文章
     *
     * @param categoryIds 分类id
     * @return list
     */
    @Select({"<script>",
            "select a.*" +
                    "from article_info as a, article_category as ac,category_info as c ",
            "where c.id = ac.category_id and a.id = ac.article_id ",
            "and c.id in",
            "  <foreach item= 'categoryIds' index= 'index' collection= 'list'",
            "      open='(' separator=',' close=')'>",
            "        #{categoryId}",
            "  </foreach>",
            "order by create_by desc",
            "</script>"})
    List<ArticleInfo> listArticleInfoByCategory(List<Long> categoryIds);
}
