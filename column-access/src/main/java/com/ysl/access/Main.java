package com.ysl.access;

import com.ysl.access.criteria.SysAccessCriteria;
import com.ysl.access.dao.MarketDataMapper;
import com.ysl.access.to.MarketData;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 程序入口
 * @autor YSL
 * 2018-08-21 15:55
 */
public class Main {

    // psvm  sout
    public static void main(String[] args) throws IOException {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        MarketDataMapper marketDataMapper = (MarketDataMapper) ctx.getBean("marketDataMapper");

        // id 1 测试两个角色的权限
        // id 4 测试用户权限优先级高于角色权限

        MarketData marketData = marketDataMapper.getMarketDataByAccess(new SysAccessCriteria(4, "market_data"));
        System.err.println(marketData);

        System.out.println("-------------------------------");

        //marketData = marketDataMapper.getAll();
        //System.out.println(marketData);

    }
}
