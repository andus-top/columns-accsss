package com.ysl.access.to;

/**
 * 角色与表字段对应的权限to
 * @table sys_role_table
 * @autor YSL
 * 2018-08-24 13:20
 */
public class SysRoleTable {
    private Integer roleId; // 角色id
    private String tbName; // 表名
    private String tbColumnInclue; // 该角色权限内的字段

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getTbName() {
        return tbName;
    }

    public void setTbName(String tbName) {
        this.tbName = tbName;
    }

    public String getTbColumnInclue() {
        return tbColumnInclue;
    }

    public void setTbColumnInclue(String tbColumnInclue) {
        this.tbColumnInclue = tbColumnInclue;
    }
}
