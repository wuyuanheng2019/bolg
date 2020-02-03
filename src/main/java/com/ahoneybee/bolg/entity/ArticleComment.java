package com.ahoneybee.bolg.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章评论关联表
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ArticleComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章主键
     */
    private Integer articleId;

    /**
     * 评论主键
     */
    private Integer commentId;

    /**
     * 是否被删除: 0为没有
     */
    private Boolean effective;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createPersion;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    private String updatePersion;


}
