package com.ahoneybee.bolg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 人员表
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

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
     * 用户ip
     */
    private String ip;

    /**
     * 用户登陆次数
     */
    private Long num;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
