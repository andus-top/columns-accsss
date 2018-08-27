package com.ysl.access.dao;

import com.ysl.access.criteria.SysAccessCriteria;
import com.ysl.access.to.MarketData;

/**
 * 市场统计数据dao
 * @table market_data
 * @autor YSL
 * 2018-08-24 10:46
 */
public interface MarketDataMapper extends BaseMapper{

    /**
     * 根据用户权限查询
     * @param sysAccessCriteria 封装了用户权限的实体类
     * @return 市场统计数据，无权限的字段为空
     */
    public MarketData getMarketDataByAccess(SysAccessCriteria sysAccessCriteria);

    /**
     * 获取所有数据
     * @return
     */
    public MarketData getAll();
}
