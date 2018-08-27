package com.ysl.access.to;

/**
 * 角色to
 * @table sys_role
 * @autor YSL
 * 2018-08-24 13:17
 */
public class SysRole {
    private Integer id;
    private String name; // 角色名称
    private String desc; // 角色描述

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
