package com.breeze.enums;

import lombok.Getter;

/**
 * @author Created by wdnnccey
 * @date 2019-07-06 23:05
 * @Description: 订单状态相关
 */
@Getter
public enum OrderStatusEnum {

    ORDER_PAID(1, "订单已支付"),
    ORDER_UNPAID(2, "订单未支付"),
    ORDER_FINISH(3, "订单已完成"),
    ORDER_CANCEL(4,"订单已取消"),
    ORDER_REFUND(5,"订单已退款");

    private Integer code;

    private String name;

    OrderStatusEnum(Integer code) {
        this.code = code;
    }

    OrderStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
