package com.breeze.pojo;

/**
 * @author Created by wdnnccey
 * @date 2019-07-01 09:15
 * @Description: 商品属性类别
 */
public class ProductAttrKey {

    /**
     * 商品属性类别Id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 备注名称（中文）
     */
    private String displayName;

    public ProductAttrKey() {
    }

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "ProductAttrKey{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}