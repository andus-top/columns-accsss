package com.ysl.access.dao;

import com.ysl.access.to.SysRoleTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色表字段映射dao
 * @author YSL
 * 2018-08-24 13:41
 */
public interface SysRoleTableMapper {

    /**
     * 根据角色id和表名查询角色拥有的权限字段
     * @param roleIds 角色id，逗号隔开的值
     * @param tbName 表名
     * @return List<SysRoleTable> 角色拥有的该表的所有权限to的集合
     */
    public List<SysRoleTable> getRoleTableByRoleIdAndTbName(@Param("roleIds") List<String> roleIds, @Param("tbName") String tbName);
}
