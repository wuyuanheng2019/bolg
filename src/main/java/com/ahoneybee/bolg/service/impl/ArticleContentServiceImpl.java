package com.ahoneybee.bolg.service.impl;

import com.ahoneybee.bolg.entity.ArticleContent;
import com.ahoneybee.bolg.mapper.ArticleContentMapper;
import com.ahoneybee.bolg.service.IArticleContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章内容 服务实现类
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@Service
public class ArticleContentServiceImpl extends ServiceImpl<ArticleContentMapper, ArticleContent> implements IArticleContentService {

}
