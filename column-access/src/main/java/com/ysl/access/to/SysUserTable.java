package com.ysl.access.to;

/**
 * 用户与表字段对应的权限to 优先级高于 SysRoleTableMapper
 * @table sys_user_table
 * @autor YSL
 * 2018-08-24 13:25
 */
public class SysUserTable {
    private Integer userId; // 角色id
    private String tbName; // 表名
    private String tbColumnInclue; // 该角色权限内的字段

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
