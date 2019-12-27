package com.breeze.exception;

import com.breeze.enums.ResultStatusEnum;

/**
 * @author Created by wdnnccey
 * @date 2019-07-19 13:29
 */
public class PaymentException extends RuntimeException {

    private Integer code;

    public PaymentException(ResultStatusEnum resultStatusEnum) {
        super(resultStatusEnum.getName());

        this.code = resultStatusEnum.getCode();
    }

    public PaymentException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}