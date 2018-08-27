package com.ysl.access.criteria;

/**
 * 封装权限to， 用于查询语句， 转换用户权限与角色权限
 * @table 无
 * @autor YSL
 * 2018-08-21 16:17
 */
public class SysAccessCriteria {
    private Integer userId;// 用户id
    private String tbName;// 表名
    private String accesscol; // 有权限的列

    public SysAccessCriteria() {}

    public SysAccessCriteria(Integer userId, String tbName) {
        this.userId = userId;
        this.tbName = tbName;
    }

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

    public String getAccesscol() {
        return accesscol;
    }

    public void setAccesscol(String accesscol) {
        this.accesscol = accesscol;
    }
}
