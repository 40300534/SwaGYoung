package com.breeze.pojo;

import io.swagger.models.auth.In;

import java.util.Date;

/**
 * @author TianXu
 * @create 2019/8/2  14:51
 * @Description TODO
 */
public class OrderTracking {
    /**
     * Id
     */
    private Long id;
    /**
     * 订单Id
     */
    private Long orderId;
    /**
     * 快递公司Id
     */
    private Integer shippingCompanyId;
    /**
     * 运单号
     */
    private String trackingNumber;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getShippingCompanyId() {
        return shippingCompanyId;
    }

    public void setShippingCompanyId(Integer shippingCompanyId) {
        this.shippingCompanyId = shippingCompanyId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
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

    @Override
    public String toString() {
        return "OrderTracking{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", shippingCompanyId=" + shippingCompanyId +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
