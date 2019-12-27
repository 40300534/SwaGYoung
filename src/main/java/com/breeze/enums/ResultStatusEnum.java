package com.breeze.enums;

import lombok.Getter;

/**
 * @author  TianXu
 * @date 2019-7-17 12:49:05
 * @Description: Result状态码
 */
@Getter
public enum ResultStatusEnum {

    SUCCESS(200, "成功"),
    ERROR(1, "失败");

    private Integer code;

    private String name;

    ResultStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
