package com.ysl.access.dao;

import com.ysl.access.to.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色映射dao
 * @table sys_user_role
 * @autor YSL
 * 2018-08-24 13:32
 */
public interface SysUserRoleMapper {

    /**
     * 根据用户id获取用户所有的角色
     * @param userId 用户id
     * @return 用户所属的角色
     */
    public List<SysUserRole> getUserRoleByUserId(@Param("userId") Integer userId);
}
