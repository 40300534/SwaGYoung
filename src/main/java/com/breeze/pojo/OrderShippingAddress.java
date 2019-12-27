package com.breeze.pojo;

/**
 * @author TianXu
 * @create 2019/8/2  15:10
 * @Description TODO
 */
public class OrderShippingAddress {
    /**
     * id
     */
    private Long id;
    /**
     * 订单Id
     */
    private Long orderId;
    /**
     * 收货地址
     */
    private Long shippingAddressId;

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

    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    @Override
    public String toString() {
        return "OrderShippingAddress{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", shippingAddressId=" + shippingAddressId +
                '}';
    }
}
