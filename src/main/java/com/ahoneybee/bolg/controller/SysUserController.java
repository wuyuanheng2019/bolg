package com.ahoneybee.bolg.controller;


import com.ahoneybee.bolg.entity.SysUser;
import com.ahoneybee.bolg.service.ISysUserService;
import com.ahoneybee.bolg.util.MyPages;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 人员表 前端控制器
 * 路径 admin(管理员权限)
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

    /**
     * 人员service
     */
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


    @GetMapping("/any/getUserByIp")
    @ApiOperation(value = "通过ip查找用户", notes = "通过ip查找用户")
    public SysUser getUserByIp(HttpServletRequest request) {
        return userService.getByIp(request.getHeader("X-Real-Ip"));
    }


    @GetMapping("/admin/listUser")
    @ApiOperation(value = "用户列表", notes = "用户列表")
    public PageInfo<SysUser> listUser(MyPages pages) {
        PageHelper.startPage(pages.getPage(), pages.getSize());
        return new PageInfo<>(userService.list());
    }


    @PostMapping("/any/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)
    })
    @ApiOperation(value = "登陆接口", notes = "登陆接口")
    public String login(String username, String password) {
        return userService.login(username, password);
    }
}