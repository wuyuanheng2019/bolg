package com.ahoneybee.bolg.service.impl;

import com.ahoneybee.bolg.entity.ArticleInfo;
import com.ahoneybee.bolg.entity.vo.ArticleInfoCategoryVo;
import com.ahoneybee.bolg.mapper.ArticleInfoMapper;
import com.ahoneybee.bolg.service.IArticleInfoService;
import com.ahoneybee.bolg.service.ICategoryInfoService;
import com.ahoneybee.bolg.util.MyPages;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    private final ICategoryInfoService categoryInfoService;

    public ArticleInfoServiceImpl(ICategoryInfoService categoryInfoService) {
        this.categoryInfoService = categoryInfoService;
    }

    @Override
    public List<ArticleInfo> listArticleInfoByTime(MyPages myPages) {
        PageHelper.startPage(myPages.getPage(), myPages.getSize());
        return lambdaQuery().orderByDesc(ArticleInfo::getCreateTime).list();
    }

    @Override
    public PageInfo<ArticleInfoCategoryVo> listArticleInfo(MyPages myPages) {

        //注意并发安全问题
        List<ArticleInfoCategoryVo> voList = Collections.synchronizedList(new ArrayList<>());
        listArticleInfoByTime(myPages).parallelStream()
                .forEach(articleInfo ->
                        voList.add(new ArticleInfoCategoryVo(articleInfo,
                                categoryInfoService.listCategoryByArticleId(articleInfo.getId())))
                );
        return new PageInfo<>(voList);
    }
}
