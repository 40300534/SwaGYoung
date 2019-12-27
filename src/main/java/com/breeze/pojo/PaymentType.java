package com.breeze.pojo;

/**
 * @author Created by wdnnccey
 * @date 2019-07-01 08:54
 * @Description: 支付方式表
 */
public class PaymentType {

    /**
     * id
     */
    private Integer id;

    /**
     * 支付方法名字
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
        return "PaymentType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}