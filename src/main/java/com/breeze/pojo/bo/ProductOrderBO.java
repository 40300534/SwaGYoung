package com.breeze.pojo.bo;

import java.math.BigDecimal;

/**
 * @author TianXu
 * @create 2019/7/22  15:49
 * @Description 这是一个返回订单相关商品信息 名字or图片 的BO
 */
public class ProductOrderBO {
    /**
     * 商品订单ID
     */
    private Long productOrderId;
    /**
     * 商品Id
     */
    private Long productId;
    /**
     * 商品名字
     */
    private String productName;
    /**
     * 商品图片
     */
    private String productImage;

    /**
     * 成交价格
     *
     */
    private BigDecimal soldPrice;

    /**
     * 数目
     */
    private Integer quantity;

    /**
     * 省
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 地址
     */
    private String address;

    public Long getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(Long productOrderId) {
        this.productOrderId = productOrderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public BigDecimal getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(BigDecimal soldPrice) {
        this.soldPrice = soldPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
