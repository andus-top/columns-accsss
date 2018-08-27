package com.ysl.access.service;


import com.ysl.access.criteria.SysAccessCriteria;

/**
 * 权限模块
 * @table sys_role_table, sys_user_table
 * @autor YSL
 * 2018-08-24 14:06
 */
public interface SysAccess {
    
    /**
     * 根据用户id和表名获取权限
     * 当用户有单独的用户权限时，以用户权限为准
     * 否则，以用户角色权限为准，用户，角色可以多个
     * @param sysAccessCriteria 封装权限to
     * @return 用户拥有该表的权限信息
     * @author YSL
     * 2018-08-24 14:10
     */
    public SysAccessCriteria getUserAceess(SysAccessCriteria sysAccessCriteria);
}
