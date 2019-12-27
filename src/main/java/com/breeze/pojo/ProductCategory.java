package com.breeze.pojo;

/**
 * 产品分类表
 * @author wdnnccey
 */
public class ProductCategory {

    /**
     * 商品分类Id
     */
    private Integer id;

    /**
     * 商品分类名称
     */
    private String name;

    public ProductCategory() {
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

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}