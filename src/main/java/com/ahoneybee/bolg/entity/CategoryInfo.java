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
 * 分类信息表
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
public class CategoryInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 文章的分类数量
     */
    private Integer number;

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
