package com.ahoneybee.bolg.service.impl;

import com.ahoneybee.bolg.entity.CommentInfo;
import com.ahoneybee.bolg.mapper.CommentInfoMapper;
import com.ahoneybee.bolg.service.ICommentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 评论详情表 服务实现类
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@Service
public class CommentInfoServiceImpl extends ServiceImpl<CommentInfoMapper, CommentInfo> implements ICommentInfoService {

    @Override
    public List<CommentInfo> listInfoByArticleId(long articleId) {
        return baseMapper.listInfoByArticleId(articleId);
    }

    @Override
    public long insertCommentInfo(CommentInfo commentInfo) {
        return baseMapper.insertCommentInfo(commentInfo);
    }
}
