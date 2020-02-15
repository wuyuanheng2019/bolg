package com.ahoneybee.bolg.interceptor;

import cn.hutool.crypto.digest.MD5;
import com.ahoneybee.bolg.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 文章分类关联表
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-09
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    ISysUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");
        String uri = httpServletRequest.getRequestURI();

        //如果路径中包含admin
        if (uri.contains("admin")) {
            if (StringUtils.isNotEmpty(token) &&
                    token.split("[.]").length == 2) {
                String[] split = token.split("[.]");

                //对比库中密码
                MD5 md5 = new MD5("WYH--@#¥".getBytes());
                String dbpassword = md5.digestHex(userService.getPasswordByUsername(split[0]));
                return split[1].equals(dbpassword);
            }
            return false;
        }

        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
