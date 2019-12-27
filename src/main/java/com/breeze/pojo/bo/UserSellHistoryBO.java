package com.breeze.pojo.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 田旭
 * @date 2019/7/10 11:40
 */
public class UserSellHistoryBO {
    /**
     * 成交的订单ID
     */
    private Long orderId;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 用户卖的商品图片
     */
    private String image;
    /**
     * 用户卖商品的名称
     */
    private String productName;
    /**
     * 最终成交价格
     */
    private BigDecimal price;
    /**
     * 最终成交的创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    public Long getOrderId() {
        return orderId;
    }

    public UserSellHistoryBO setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public UserSellHistoryBO setImage(String image) {
        this.image = image;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public UserSellHistoryBO setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UserSellHistoryBO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public UserSellHistoryBO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
