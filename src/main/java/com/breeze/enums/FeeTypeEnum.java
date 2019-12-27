package com.breeze.enums;

import lombok.Getter;

/**
 * @author Created by wdnnccey
 * @date 2019-07-04 08:30
 */
@Getter
public enum FeeTypeEnum {

    FIXED(1, "固定值"),
    PERCENT(2, "百分比");

    private Integer code;

    private String name;

    FeeTypeEnum(Integer code) {
        this.code = code;
    }

    FeeTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
