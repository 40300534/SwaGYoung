package com.breeze.pojo.dto;

import java.math.BigDecimal;


/**
 * @author 莫奇
 *
 * 购物车展示.
 */
public class ShoppingCartDTO {

    /**
     * 商品Id
     */
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品价格
     */
    private BigDecimal price;
    /**
     * 图片地址
     */
    private String imageFolder;
    /**
     * 数量
     */
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageFolder() {
        return imageFolder;
    }

    public void setImageFolder(String imageFolder) {
        this.imageFolder = imageFolder;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShoppingCartDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imageFolder='" + imageFolder + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
