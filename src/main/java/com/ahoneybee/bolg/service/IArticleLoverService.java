package com.ahoneybee.bolg.service;

import com.ahoneybee.bolg.entity.ArticleLover;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-05
 */
public interface IArticleLoverService extends IService<ArticleLover> {

    /**
     * save
     *
     * @param aid 文章id
     * @param ip  用户ip
     */
    void insertArticleLover(long aid, String ip);


    /**
     * delete
     *
     * @param aid 文章id
     * @param ip  用户ip
     */
    void deleteArticleLover(long aid, String ip);

    /**
     * 查看该ip用户是否点赞
     *
     * @param ip  用户ip
     * @param aid 文章id
     * @return 如果未点赞返回-1，点赞返回id
     */
    long getLoveTrueOrFalse(String ip, long aid);
}
