package com.ahoneybee.bolg.service.impl;

import com.ahoneybee.bolg.entity.ArticleInfo;
import com.ahoneybee.bolg.mapper.ArticleInfoMapper;
import com.ahoneybee.bolg.service.IArticleInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章信息 服务实现类
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@Service
public class ArticleInfoServiceImpl extends ServiceImpl<ArticleInfoMapper, ArticleInfo> implements IArticleInfoService {

}
