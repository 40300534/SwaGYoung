package com.breeze.enums;

import lombok.Getter;

/**
 * @author Created by wdnnccey
 * @date 2019-07-04 10:57
 * @Description: 交易类型4种
 */
@Getter
public enum TransactionTypeEnum {

    BID_BUY(1, "出价买"),
    BID_SELL(2, "出价卖"),
    NOW_BUY(3, "立即买"),
    NOW_SELL(4, "立即卖");

    private Integer code;

    private String name;

    TransactionTypeEnum(Integer code) {
        this.code = code;
    }

    TransactionTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
