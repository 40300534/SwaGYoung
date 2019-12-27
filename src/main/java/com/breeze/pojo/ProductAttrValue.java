package com.breeze.pojo;

/**
 * @author Created by wdnnccey
 * @date 2019-07-01 09:20
 * @Description: 商品属性
 */
public class ProductAttrValue {
    /**
    * id
    */
    private Integer id;

    /**
    * 名称
    */
    private String name;

    /**
    * 商品属性类别Id
    */
    private Integer attrKeyId;

    /**
    * 商品id
    */
    private Long productId;

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

    public Integer getAttrKeyId() {
        return attrKeyId;
    }

    public void setAttrKeyId(Integer attrKeyId) {
        this.attrKeyId = attrKeyId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductAttrValue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", attrKeyId=" + attrKeyId +
                ", productId=" + productId +
                '}';
    }
}