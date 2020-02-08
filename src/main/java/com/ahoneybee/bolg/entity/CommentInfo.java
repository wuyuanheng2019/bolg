package com.ahoneybee.bolg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 评论详情表
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CommentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 评论信息
     */
    private String content;

    /**
     * 联系方式
     */
    private String connect;

    /**
     * 昵称
     */
    private String name;

    /**
     * 访问ip
     */
    private String ip;

    /**
     * 评论人id
     */
    private Long userId;

    /**
     * 父id
     */
    private Long parentId;

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
