package com.ahoneybee.bolg.mapper;

import com.ahoneybee.bolg.entity.ArticleInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

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
}
