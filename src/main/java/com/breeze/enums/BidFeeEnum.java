package com.breeze.enums;

import lombok.Getter;

/**
 * @author Created by wdnnccey
 * @date 2019-07-31 12:13
 * @Description: 出价费用
 */
@Getter
public enum BidFeeEnum {

    BID_MULTIPLE(10,"出价倍数");

    private Integer code;

    private String name;

    BidFeeEnum(Integer code) {
        this.code = code;
    }

    BidFeeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
