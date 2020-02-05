package com.ahoneybee.bolg.service.impl;

import com.ahoneybee.bolg.entity.SysUser;
import com.ahoneybee.bolg.mapper.SysUserMapper;
import com.ahoneybee.bolg.service.ISysUserService;
import com.ahoneybee.bolg.util.Ip2Region;
import com.ahoneybee.bolg.util.OperateByUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 人员表 服务实现类
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public boolean saveUser(HttpServletRequest request) {
        //创建标志
        boolean flag = true;

        try {

            //获取并检测用户ip
            String ip = request.getHeader("X-Real-Ip");
            Ip2Region.judgeIp(ip);
            SysUser user = getByIp(ip);

            if (ObjectUtils.isEmpty(user)) {
                //创建访客,用来记录ip访客查询量
                save(new SysUser()
                        .setIp(ip)
                        .setRole("ANY")
                        .setName("VISITOR")
                        .setRegion(Ip2Region.sendGet(ip))
                        .setBrowser(OperateByUtils.getOsAndBrowserInfo(request)));
            }

            lambdaUpdate()
                    .eq(SysUser::getIp, user.getIp())
                    .set(SysUser::getNum, user.getNum() + 1).update();

        } catch (Exception e) {
            flag = false;
            log.error(e.getMessage(), e);
        }

        return flag;
    }

    @Override
    public SysUser getByIp(String ip) {
        return lambdaQuery()
                .eq(StringUtils.isNotEmpty(ip), SysUser::getIp, ip)
                .one();
    }

    @Override
    public long getUserNum() {
        return baseMapper.selectCount(null);
    }

    @Override
    public long getUserLogNum() {
        return baseMapper.selectUserLogNum();
    }


}
