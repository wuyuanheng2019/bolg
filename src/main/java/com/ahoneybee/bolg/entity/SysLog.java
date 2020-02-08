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
 * 系统日志
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@Data
@TableName("sys_log")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SysLog implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 访问的url
     */
    private String operateUrl;

    /**
     * 访问的浏览器
     */
    private String operateBy;

    /**
     * 访问时间
     */
    private LocalDateTime createTime;

    /**
     * 访问人
     */
    private String createPersion;


}
