package com.breeze.pojo;

/**
 * @author Created by wdnnccey
 * @date 2019-07-01 08:46
 * @Description: 产地表，关联product表
 */
public class ProductOrigin {

    /**
    * Id
    */
    private Integer id;

    /**
    * 产地名称
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
        return "ProductOrigin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}