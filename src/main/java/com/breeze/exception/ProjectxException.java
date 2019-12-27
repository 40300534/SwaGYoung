package com.breeze.exception;

import com.breeze.constants.ResultMessage;
import com.breeze.enums.ResultStatusEnum;

/**
 * @author Created by wdnnccey
 * @date 2019-07-19 13:29
 * @description 全局异常
 */
public class ProjectxException extends RuntimeException {

    private Integer code;

    public ProjectxException(ResultStatusEnum resultStatusEnum) {
        super(resultStatusEnum.getName());

        this.code = resultStatusEnum.getCode();
    }

    public ProjectxException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}