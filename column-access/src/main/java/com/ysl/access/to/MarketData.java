package com.ysl.access.to;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 市场数据to
 * @table market_data
 * @autor YSL
 * 2018-08-24 10:52
 */
public class MarketData {
    private BigInteger id;
    private BigInteger  addUserCount;
    private BigDecimal userCost;
    private BigDecimal allRecharge;
    private BigDecimal todayUserpay;
    private BigInteger todayAddUserCount;
    private BigDecimal aUserCost;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getAddUserCount() {
        return addUserCount;
    }

    public void setAddUserCount(BigInteger addUserCount) {
        this.addUserCount = addUserCount;
    }

    public BigDecimal getUserCost() {
        return userCost;
    }

    public void setUserCost(BigDecimal userCost) {
        this.userCost = userCost;
    }

    public BigDecimal getAllRecharge() {
        return allRecharge;
    }

    public void setAllRecharge(BigDecimal allRecharge) {
        this.allRecharge = allRecharge;
    }

    public BigDecimal getTodayUserpay() {
        return todayUserpay;
    }

    public void setTodayUserpay(BigDecimal todayUserpay) {
        this.todayUserpay = todayUserpay;
    }

    public BigInteger getTodayAddUserCount() {
        return todayAddUserCount;
    }

    public void setTodayAddUserCount(BigInteger todayAddUserCount) {
        this.todayAddUserCount = todayAddUserCount;
    }

    public BigDecimal getaUserCost() {
        return aUserCost;
    }

    public void setaUserCost(BigDecimal aUserCost) {
        this.aUserCost = aUserCost;
    }

    @Override
    public String toString() {
        return "MarketData{" +
                "id=" + id +
                ", addUserCount=" + addUserCount +
                ", userCost=" + userCost +
                ", allRecharge=" + allRecharge +
                ", todayUserpay=" + todayUserpay +
                ", todayAddUserCount=" + todayAddUserCount +
                ", aUserCost=" + aUserCost +
                '}';
    }
}
