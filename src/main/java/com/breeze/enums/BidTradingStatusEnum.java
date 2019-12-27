package com.breeze.enums;

import lombok.Getter;

/**
 * @author Created by wdnnccey
 * @date 2019-07-18 11:28
 * @Description: 买卖产品的状态
 */
@Getter
public enum BidTradingStatusEnum {

    PRODUCT_LISTED_UNPAID(1, "未上架-未支付押金"),
    PRODUCT_LISTED_PAID(2, "已上架-已支付押金"),
    PRODUCT_BOOKING(3, "已有买/卖家预订"),
    PRODUCT_FINISH(4,"已完成"),
    PRODUCT_CANCEL(5,"已取消"),
    PRODUCT_NEW_BIDDING(6,"商品已更新出价");

    private Integer code;

    private String message;

    BidTradingStatusEnum(Integer code) {
        this.code = code;
    }

    BidTradingStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
