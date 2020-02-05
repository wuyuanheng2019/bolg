package com.ahoneybee.bolg.controller;


import com.ahoneybee.bolg.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 人员表 前端控制器
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "SysUserController", tags = {"用户接口文档"})
public class SysUserController {

    @Autowired
    private ISysUserService userService;

    @ApiOperation(value = "用户保存", notes = "用户保存")
    @PostMapping("/any/saveUser")
    public boolean postUser(HttpServletRequest request) {
        return userService.saveUser(request);
    }


    @ApiOperation(value = "用户数量", notes = "用户数量")
    @GetMapping("/any/getUserNum")
    public long getUserNum() {
        return userService.getUserNum();
    }


    @ApiOperation(value = "总访问量", notes = "总访问量")
    @GetMapping("/any/getUserLogNum")
    public long getUserLogNum() {
        return userService.getUserLogNum();
    }
}
