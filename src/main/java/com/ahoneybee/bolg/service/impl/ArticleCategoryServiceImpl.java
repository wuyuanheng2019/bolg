package com.ahoneybee.bolg.service.impl;

import com.ahoneybee.bolg.entity.ArticleCategory;
import com.ahoneybee.bolg.mapper.ArticleCategoryMapper;
import com.ahoneybee.bolg.service.IArticleCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章分类关联表 服务实现类
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements IArticleCategoryService {

}
