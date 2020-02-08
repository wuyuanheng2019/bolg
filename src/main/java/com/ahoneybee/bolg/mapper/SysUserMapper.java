package com.ahoneybee.bolg.mapper;

import com.ahoneybee.bolg.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 人员表 Mapper 接口
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-04
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 获取用户总访问量
     *
     * @return 总量
     */
    @Select(value = "select COALESCE(CAST(SUM(`num`) AS SIGNED), 0) AS 'sum' from sys_user")
    long selectUserLogNum();

    /**
     * 获取密码
     *
     * @param username 用户名
     * @return pwd
     */
    @Select("select password from sys_me where name = #{username} or u_id = (select id from sys_user where name = #{username})")
    String getPasswordByUsername(String username);
}
