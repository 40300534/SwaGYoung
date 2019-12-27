package com.breeze.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Created by wdnnccey
 * @date 2019-07-09 09:18
 * @Description: 一句话描述该类的功能
 */
public class ProductAsk {
    /**
    * id
    */
    private Long id;

    /**
    * 商品id
    */
    private Long productId;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 价格
    */
    private BigDecimal price;

    /**
    * 库存
    */
    private Integer inventory;

    /**
    * 卖家费用
    */
    private String fee;

    /**
     * 是否有效
     */
    private Integer isActive;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
     * 更新时间
     *
     * @return
     */
    private Date updateTime;

    /**
     * 用户
     * @return
     */
    private Long updater;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    @Override
    public String toString() {
        return "ProductAsk{" +
                "id=" + id +
                ", productId=" + productId +
                ", userId=" + userId +
                ", price=" + price +
                ", inventory=" + inventory +
                ", fee='" + fee + '\'' +
                ", isActive=" + isActive +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updater=" + updater +
                '}';
    }
}