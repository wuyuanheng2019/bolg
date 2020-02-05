package com.ahoneybee.bolg.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 前端传入数据
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@Data
@ApiModel(value = "分页查询对象", description = "分页查询对象")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MyPages {

    /**
     * 每页有多少条
     */
    @ApiModelProperty(value = "每页展示多少条", example = "6", required = true)
    private int size;

    /**
     * 当前第几页
     */
    @ApiModelProperty(value = "第几页", example = "1", required = true)
    private int page;

    /**
     * 其他信息
     */
//    @NotNull(message = "当前页不能为空！")
//    @Pattern(regexp = "[0-9]", message = "当前页格式错误")
    @ApiModelProperty(value = "其他信息", example = "其他信息")
    private Object others;

}
