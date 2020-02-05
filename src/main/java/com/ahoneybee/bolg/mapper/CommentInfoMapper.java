package com.ahoneybee.bolg.mapper;

import com.ahoneybee.bolg.entity.CommentInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 评论详情表 Mapper 接口
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
public interface CommentInfoMapper extends BaseMapper<CommentInfo> {

    @Select(value = "SELECT info.* " +
            "FROM comment_info info " +
            "LEFT JOIN article_comment ar " +
            "ON ar.comment_id = info.id " +
            "WHERE ar.article_id = #{articleId}")
    List<CommentInfo> listInfoByArticleId(long articleId);
}
