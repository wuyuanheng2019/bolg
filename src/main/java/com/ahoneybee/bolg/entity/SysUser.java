package com.ahoneybee.bolg.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 人员表
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户角色
     */
    private String role;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * ip对应的地区
     */
    private String region;

    /**
     * 联系方式
     */
    private String connect;

    /**
     * 用户ip
     */
    private String ip;

    /**
     * 用户登陆次数
     */
    private Integer num;

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
