package com.breeze.enums;

import lombok.Getter;

/**
 * @author Created by wdnnccey
 * @date 2019-07-05 15:18
 * @Description: 一句话描述该类的功能
 */
@Getter
public enum PaymentTypeEnum {

    ALIPAY(1, "支付宝"),
    WECHATPAY(2, "微信");

    private Integer code;

    private String name;

    PaymentTypeEnum(Integer code) {
        this.code = code;
    }

    PaymentTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
