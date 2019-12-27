package com.breeze.pojo;


/**
 * @author TianXu
 * @create 2019/8/2  14:55
 * @Description TODO
 */
public class OrderStatusType {
    /**
     * Id
     */
    private Integer id;
    /**
     * 订单状态
     */
    private Integer orderStateId;
    /**
     * 状态详情
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderStateId() {
        return orderStateId;
    }

    public void setOrderStateId(Integer orderStateId) {
        this.orderStateId = orderStateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrderStatusType{" +
                "id=" + id +
                ", orderStateId=" + orderStateId +
                ", name='" + name + '\'' +
                '}';
    }
}
