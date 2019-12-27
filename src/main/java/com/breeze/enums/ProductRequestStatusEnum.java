package com.breeze.enums;

import lombok.Getter;

/**
 * @author 莫奇
 */
@Getter
public enum ProductRequestStatusEnum {

    PRODUCT_NEW_REVIEW(1, "新增商品待审核"),
    PRODUCT_APPROVED_REVIEW(2,"商品审核通过"),
    PRODUCT_DENIED_REVIEW(3, "商品审核未通过");

    private Integer code;

    private String name;

    ProductRequestStatusEnum(Integer code) {
        this.code = code;
    }

    ProductRequestStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}
