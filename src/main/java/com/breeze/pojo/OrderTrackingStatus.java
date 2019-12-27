package com.breeze.pojo;

import java.util.Date;

/**
 * @author TianXu
 * @create 2019/8/2  14:48
 * @Description TODO
 */
public class OrderTrackingStatus {
    /**
     * id
     */
    private Long id;
    /**
     * 订单-运单号Id
     */
    private Long orderTrackingId;
    /**
     * 状态类型
     */
    private Integer statusTypeId;
    /**
     * 创建时间
     */
    private Date create_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderTrackingId() {
        return orderTrackingId;
    }

    public void setOrderTrackingId(Long orderTrackingId) {
        this.orderTrackingId = orderTrackingId;
    }

    public Integer getStatusTypeId() {
        return statusTypeId;
    }

    public void setStatusTypeId(Integer statusTypeId) {
        this.statusTypeId = statusTypeId;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "OrderTrackingStatus{" +
                "id=" + id +
                ", orderTrackingId=" + orderTrackingId +
                ", statusTypeId=" + statusTypeId +
                ", create_time=" + create_time +
                '}';
    }
}
