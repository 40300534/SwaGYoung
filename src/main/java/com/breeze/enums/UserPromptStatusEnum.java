package com.breeze.enums;

import lombok.Getter;

/**
 * @author TianXu
 * @create 2019/8/1  11:18
 * @Description 用户换绑手机-更改密码的状态
 */
@Getter
public enum UserPromptStatusEnum {

    USER_PASSWORD_SAME_ERROR(101, "输入的密码和原密码一致不需要再次修改"),
    USER_UPDATE_PHONE_SUCCESS(200,"修改手机号成功"),
    USER_ACCOUNT_NOT_FOUND(209, "账户不存在"),
    USER_ACCOUNT_BOUND_BY_OTHERS(4018, "账户已经被其他用户绑定"),
    USER_SERVER_ERROR(500,"服务器内部故障");

    private Integer code;
    private String  message;

    UserPromptStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
