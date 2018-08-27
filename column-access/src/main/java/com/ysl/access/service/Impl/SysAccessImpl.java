package com.ysl.access.service.Impl;

import com.ysl.access.criteria.SysAccessCriteria;
import com.ysl.access.dao.SysRoleTableMapper;
import com.ysl.access.dao.SysUserRoleMapper;
import com.ysl.access.dao.SysUserTableMapper;
import com.ysl.access.to.SysRoleTable;
import com.ysl.access.to.SysUserRole;
import com.ysl.access.to.SysUserTable;
import com.ysl.access.service.SysAccess;
import com.ysl.access.utils.CollectionUtil;
import com.ysl.access.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * 权限模块实现类
 * @table
 * @autor YSL
 * 2018-08-24 14:07
 */
@Service
public class SysAccessImpl implements SysAccess {

    @Autowired
    private SysUserTableMapper userTableMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysRoleTableMapper roleTableMapper;

    /**
     * 根据用户id和表名获取权限
     * 当用户有单独的用户权限时，以用户权限为准
     * 否则，以用户角色权限为准，用户，角色可以多个
     * @param sysAccessCriteria 封装权限to
     * @return 用户拥有该表的权限信息
     * @author YSL
     * 2018-08-24 14:19
     */
    @Override
    public SysAccessCriteria getUserAceess(SysAccessCriteria sysAccessCriteria) {
        if(sysAccessCriteria.getUserId() == null || sysAccessCriteria.getUserId() < 0
                || sysAccessCriteria.getTbName() == null || sysAccessCriteria.getTbName().trim().length() <= 0){
            System.err.println("SysAccessImpl.getUserAceess=>参数有误");
            return null;
        }

        // 获取该用户单独设置的权限
        SysUserTable sysUserTable = userTableMapper.getUserTableByUserIdAndTbName(sysAccessCriteria.getUserId(), sysAccessCriteria.getTbName());

        // 该用户是否单独设置过权限
        if(sysUserTable != null && sysUserTable.getTbColumnInclue() != null && sysUserTable.getTbColumnInclue().trim().length() > 0){
            sysAccessCriteria.setAccesscol(sysUserTable.getTbColumnInclue());
            return sysAccessCriteria;
        }

        /**该用户没有被单独设置权限，就去查询该用户角色，再获取角色权限赋给用户**/

        // 该用户担任的角色
        List<SysUserRole> sysUserRoleList = userRoleMapper.getUserRoleByUserId(sysAccessCriteria.getUserId());

        // 拼接用户所属角色id
        List roleIdList = new Vector();
        if(sysUserRoleList != null && !sysUserRoleList.isEmpty()){
            for (SysUserRole sysUserRole : sysUserRoleList) {
                roleIdList.add(sysUserRole.getRoleId());
            }
        }

        // 根据roleId获取角色拥有目标表的权限字段
        List<SysRoleTable> sysRoleTables = roleTableMapper.getRoleTableByRoleIdAndTbName(roleIdList, sysAccessCriteria.getTbName());
        // 拼接
        StringBuffer allCols = new StringBuffer("");
        if(sysRoleTables != null && !sysRoleTables.isEmpty()){
            for (SysRoleTable sysRoleTable: sysRoleTables) {
                allCols.append(sysRoleTable.getTbColumnInclue());
                allCols.append(",");
            }
        }
        // 该用户所属角色的在目标表上的全部权限字段
        String allColumns = StringUtil.replaceLastComma(allCols.toString());

        // 去除目标表中重复的字段
        List<String> columnsList = CollectionUtil.removeDuplicate(Arrays.asList(allColumns.split(",")));

        // 拼接
        StringBuffer cols = new StringBuffer("");
        for (String c: columnsList) {
            cols.append(c);
            cols.append(",");
        }
        // 该用户所属角色的在目标表上的权限字段，去除重复字段与最后一个逗号
        String columns = StringUtil.replaceLastComma(cols.toString());

        sysAccessCriteria.setAccesscol(columns);

        return sysAccessCriteria;

    }
}
