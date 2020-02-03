package com.ahoneybee.bolg.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统日志
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

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
