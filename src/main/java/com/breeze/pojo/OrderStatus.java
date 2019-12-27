package com.breeze.pojo;

import com.mysql.cj.log.Log;

import java.util.Date;

/**
 * @author Created by tx
 * @date 2019-8-2 15:03:17
 */
public class OrderStatus {
    /**
    * id
    */
    private Long id;
    /**
     * 订单Id
     */
    private Long orderId;
    /**
     * 状态类型
     */
    private Integer statusTypeId;
    /**
    * 描述
    */
    private String comment;

    /**
     * 创建时间
     */
    private Date createTime;

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

    public Integer getStatusTypeId() {
        return statusTypeId;
    }

    public void setStatusTypeId(Integer statusTypeId) {
        this.statusTypeId = statusTypeId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", statusTypeId=" + statusTypeId +
                ", comment='" + comment + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}