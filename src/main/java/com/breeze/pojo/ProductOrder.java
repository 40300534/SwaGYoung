package com.breeze.pojo;

import java.math.BigDecimal;

/**
 * @author Created by wdnnccey
 * @date 2019-07-08 10:15
 * @Description: 一句话描述该类的功能
 */
public class ProductOrder {
    /**
     * 商品订单Id
     */
    private Long id;

    /**
     * 商品Id
     */
    private Long productId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 成交价格
     */
    private BigDecimal soldPrice;

    /**
     * 数目
     */
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
}