package com.ahoneybee.bolg.service.impl;

import com.ahoneybee.bolg.entity.ArticleCategory;
import com.ahoneybee.bolg.mapper.ArticleCategoryMapper;
import com.ahoneybee.bolg.service.IArticleCategoryService;
import com.ahoneybee.bolg.service.ICategoryInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
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

    /**
     * 分类service
     */
    private final ICategoryInfoService categoryInfoService;

    public ArticleCategoryServiceImpl(ICategoryInfoService categoryInfoService) {
        this.categoryInfoService = categoryInfoService;
    }

    @Override
    public ArticleCategory getCategoryByArticleId(Long articleId) {
        return lambdaQuery().eq(ObjectUtils.isNotEmpty(articleId), ArticleCategory::getArticleId, articleId).one();
    }

    @Override
    public void updateByArticleId(ArticleCategory articleCategory) {

        /*
         *  1 查看当前分类是否改变
         *  2 如改变，则进行修改，反之则不动
         */
        ArticleCategory one = getCategoryByArticleId(articleCategory.getArticleId());
        if (!articleCategory.getCategoryId().equals(one.getCategoryId())) {

            //当前分类 +1，之前分类 -1
            categoryInfoService.updateNumber(articleCategory.getCategoryId(), 1);
            categoryInfoService.updateNumber(one.getCategoryId(), -1);
        }
    }

}
