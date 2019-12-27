    package com.breeze.pojo;

/**
 * @author Created by wdnnccey
 * @date 2019-07-03 14:11
 * @Description: 一句话描述该类的功能
 */
public class Fee {
    /**
    * Id
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
    private Long value;

    /**
    * 值类型id
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

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
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

    @Override
    public String toString() {
        return "Fee{" +
                "id=" + id +
                ", userTypeId=" + userTypeId +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", valueTypeId=" + valueTypeId +
                ", isSeller=" + isSeller +
                '}';
    }
}