package com.ysl.access.to;

/**
 * 用户to
 * @table sys_user
 * @autor YSL
 * 2018-08-24 13:22
 */
public class SysUser {
    private Integer id; // 用户id
    private String name; // 用户名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
