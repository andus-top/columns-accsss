package com.ysl.access.dao;

import com.ysl.access.to.SysUserTable;
import org.apache.ibatis.annotations.Param;

/**
 * 用户表字段映射dao
 * @table sys_user_table
 * @autor YSL
 * 2018-08-24 13:58
 */
public interface SysUserTableMapper {

    /**
     * 根据用户id和表名查询角色拥有的权限字段
     * @param userId 用户id
     * @param tbName 表名
     * @return 用户拥有的该表的所有权限to
     */
    public SysUserTable getUserTableByUserIdAndTbName(@Param("userId") Integer userId, @Param("tbName") String tbName);
}
