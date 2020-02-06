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

    private final ISysUserService userService;

    public SysUserController(ISysUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/any/postUser")
    @ApiOperation(value = "用户保存", notes = "用户保存")
    public boolean postUser(HttpServletRequest request) {
        return userService.saveUser(request);
    }


    @GetMapping("/any/getUserNum")
    @ApiOperation(value = "用户数量", notes = "用户数量")
    public long getUserNum() {
        return userService.getUserNum();
    }


    @GetMapping("/any/getUserLogNum")
    @ApiOperation(value = "总访问量", notes = "总访问量")
    public long getUserLogNum() {
        return userService.getUserLogNum();
    }
}
