package com.ahoneybee.bolg.service.impl;

import com.ahoneybee.bolg.entity.ArticleComment;
import com.ahoneybee.bolg.entity.CommentInfo;
import com.ahoneybee.bolg.entity.SysUser;
import com.ahoneybee.bolg.mapper.ArticleCommentMapper;
import com.ahoneybee.bolg.service.IArticleCommentService;
import com.ahoneybee.bolg.service.ICommentInfoService;
import com.ahoneybee.bolg.service.ISysUserService;
import com.ahoneybee.bolg.util.Ip2Region;
import com.ahoneybee.bolg.util.OperateByUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 文章评论关联表 服务实现类
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements IArticleCommentService {

    /**
     * 人员service
     */
    private final ISysUserService sysUserService;

    private final ICommentInfoService commentInfoService;

    public ArticleCommentServiceImpl(ISysUserService sysUserService, ICommentInfoService commentInfoService) {
        this.sysUserService = sysUserService;
        this.commentInfoService = commentInfoService;
    }

    @Override
    public boolean postArticleComment(long id, CommentInfo commentInfo, HttpServletRequest request) {

        /*
         * 1 通过ip拿到用户
         * 2 判断用户是否存在 不存在则直接添加
         * 3 如存在，则判断是否为访客
         * 4 访客：修改为正式用户，反之则浏览记录自增
         * 5 对评论表进行操作
         */
        String ip = request.getHeader("X-Real-Ip");
        Ip2Region.judgeIp(ip);
        SysUser sysUser = sysUserService.getByIp(ip);

        if (ObjectUtils.isNotEmpty(sysUser)) {

            sysUserService.lambdaUpdate()
                    .eq(SysUser::getIp, sysUser.getIp())
                    .set(SysUser::getRole, "USER")
                    .set(SysUser::getNum, sysUser.getNum() + 1)
                    .set(StringUtils.isNotEmpty(sysUser.getName()),
                            SysUser::getName, commentInfo.getName())
                    .set(StringUtils.isNotEmpty(commentInfo.getConnect()),
                            SysUser::getConnect, commentInfo.getConnect())
                    .update();

        } else {
            sysUserService.save(new SysUser()
                    .setIp(ip)
                    .setRole("USER")
                    .setName(commentInfo.getName())
                    .setRegion(Ip2Region.sendGet(ip))
                    .setConnect(commentInfo.getConnect())
                    .setBrowser(OperateByUtils.getOsAndBrowserInfo(request))
            );
        }

        save(new ArticleComment().setArticleId(id).setCommentId(commentInfo.getId()));
        commentInfoService.save(commentInfo.setUserId(sysUser.getId()));

        return true;
    }
}
