package com.ysl.access.to;

/**
 * 用户角色映射to
 * @table sys_user_role
 * @autor YSL
 * 2018-08-24 13:23
 */
public class SysUserRole {
    private Integer userId; // 用户id
    private Integer roleId; // 角色id

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
