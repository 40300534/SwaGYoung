package com.breeze.pojo;

/**
 * @author Created by wdnnccey
 * @date 2019-07-01 08:32
 * @Description:  产品属性
 */
public class ProductBrand {
    /**
    * id
    */
    private Integer id;

    /**
    * 名字
    */
    private String name;

    /**
    * 介绍
    */
    private String desc;

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

    @Override
    public String toString() {
        return "ProductBrand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}