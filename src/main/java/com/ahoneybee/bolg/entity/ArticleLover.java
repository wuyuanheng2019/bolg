package com.ahoneybee.bolg.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ArticleLover implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private long id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 文章id
     */
    private Integer articleId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createPersion;


}
