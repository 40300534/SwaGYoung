package com.breeze.pojo;

/**
 * @author Created by wdnnccey
 * @date 2019-07-03 15:16
 * @Description: 一句话描述该类的功能
 */
public class FeeValueType {
    /**
    * id
    */
    private Integer id;

    /**
    * 姓名
    */
    private String name;

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

    @Override
    public String toString() {
        return "FeeValueType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}