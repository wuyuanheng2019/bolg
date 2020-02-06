package com.ahoneybee.bolg.mapper;

import com.ahoneybee.bolg.entity.ArticleLover;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-05
 */
public interface ArticleLoverMapper extends BaseMapper<ArticleLover> {

    /**
     * save
     *
     * @param aid 文章id
     * @param ip  用户ip
     */
    @Insert("insert into article_lover (article_id, user_id) values (#{aid}, (select id from sys_user where ip = #{ip}))")
    void insertArticleLover(long aid, String ip);

    /**
     * delete
     *
     * @param aid 文章id
     * @param ip  用户ip
     */
    @Delete("delete from article_lover where article_id = #{aid} and user_id = (select id from sys_user where ip = #{ip})")
    void deleteArticleLover(long aid, String ip);

    /**
     * 通过ip查看id
     *
     * @param ip  用户ip
     * @param aid 文章id
     * @return id
     */
    @Select("select su.id from article_lover al, sys_user su where su.ip = #{remoteAddr} and su.id = al.user_id and al.article_id = #{aid}")
    Long getLoveTrueOrFalse(String ip, long aid);
}
