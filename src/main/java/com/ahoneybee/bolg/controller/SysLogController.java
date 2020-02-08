package com.ahoneybee.bolg.controller;


import com.ahoneybee.bolg.entity.ArticleInfo;
import com.ahoneybee.bolg.entity.SysLog;
import com.ahoneybee.bolg.service.ISysLogService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@RestController
@RequestMapping("/sysLog")
public class SysLogController {

}
