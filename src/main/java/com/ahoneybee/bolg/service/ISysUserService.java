package com.ahoneybee.bolg.service;

import com.ahoneybee.bolg.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 人员表 服务类
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 1 通过nginx得到访客ip地址
     * 2 判断ip地址是否在user表中存在,存在则加一
     *
     * @param request 前端请求
     * @return 是否保存成功
     */
    boolean saveUser(HttpServletRequest request);

    /**
     * 通过ip来查询用户
     *
     * @param ip 用户ip地址
     * @return 详细信息
     */
    SysUser getByIp(String ip);

    /**
     * 获取用户数量
     *
     * @return 数量
     */
    long getUserNum();

    /**
     * 获取用户总访问量
     *
     * @return 总量
     */
    long getUserLogNum();

    /**
     * 获取用户密码
     *
     * @return password
     */
    String getPasswordByUsername(String username);

    /**
     * 登陆
     *
     * @param username 账号名称
     * @param password 密码
     * @return 检测结果(false 为和前台交互的规则)
     */
    String login(String username, String password);
}
