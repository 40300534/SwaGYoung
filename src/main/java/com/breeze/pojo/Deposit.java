package com.breeze.pojo;

import java.math.BigDecimal;

/**
 * @author Created by wdnnccey
 * @date 2019-07-08 20:04
 * @Description: 押金表
 */
public class Deposit {
    /**
    * id
    */
    private Integer id;

    /**
    * 用户类型id
    */
    private Integer userTypeId;

    /**
    * 名称
    */
    private String name;

    /**
    * 值
    */
    private BigDecimal value;

    /**
     * 值的类型Id
     */
    private Integer valueTypeId;

    /**
     * 是否是卖家
     */
    private Integer isSeller;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Integer getValueTypeId() {
        return valueTypeId;
    }

    public void setValueTypeId(Integer valueTypeId) {
        this.valueTypeId = valueTypeId;
    }

    public Integer getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(Integer isSeller) {
        this.isSeller = isSeller;
    }
}