package com.breeze.enums;

import lombok.Getter;

/**
 * @author Created by wdnnccey
 * @date 2019-07-06 23:05
 * @Description: 订单状态相关
 */
@Getter
public enum ImageCategoryEnum {

    PRODUCT_IMAGE(1, "产品图片"),
    USER_AVATAR_IMAGE(2, "用户头像图片"),
    USER_TRENDS_IMAGE(3, "用户动态图片"),
    FEEDBACK_IMAGE(4,"意见反馈图片");

    private Integer code;

    private String name;

    ImageCategoryEnum(Integer code) {
        this.code = code;
    }

    ImageCategoryEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
