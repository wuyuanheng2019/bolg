package com.ahoneybee.bolg.service.impl;

import com.ahoneybee.bolg.entity.ArticleLover;
import com.ahoneybee.bolg.mapper.ArticleLoverMapper;
import com.ahoneybee.bolg.service.IArticleLoverService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-05
 */
@Service
public class ArticleLoverServiceImpl extends ServiceImpl<ArticleLoverMapper, ArticleLover> implements IArticleLoverService {

    @Override
    public void insertArticleLover(long aid, String ip) {
        baseMapper.insertArticleLover(aid, ip);
    }

    @Override
    public void deleteArticleLover(long aid, String ip) {
        baseMapper.deleteArticleLover(aid, ip);
    }

    @Override
    public long getLoveTrueOrFalse(String ip, long aid) {
        return baseMapper.getLoveTrueOrFalse(ip, aid) == null ? -1 : 1;
    }
}
