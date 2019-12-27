package com.breeze.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;


/**
 * @author 莫奇
 *
 * 购物车表
 */
public class UserProductShoppingCart {

    private Long id;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 商品Id
     */
    private Long productId;

    /**
     * 加入购物车时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
    private Date createTime;

    /**
     * 数量
     */
    private Integer quantity;

    public UserProductShoppingCart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "UserProductShoppingCart{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", createTime=" + createTime +
                ", quantity=" + quantity +
                '}';
    }
}
