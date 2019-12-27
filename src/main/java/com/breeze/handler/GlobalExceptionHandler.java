package com.breeze.handler;

import com.breeze.enums.ResultStatusEnum;
import com.breeze.exception.PaymentException;
import com.breeze.exception.ProjectxException;
import com.breeze.pojo.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Created by wdnnccey
 * @date 2019-07-19 13:32
 * @Description: 异常拦截处理器
 */

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(ProjectxException.class)
    @ResponseBody
    public ResultVO exceptionHandlerByProjectxException(ProjectxException e){
        return new ResultVO(false, ResultStatusEnum.ERROR.getCode(),e.getMessage());
    }

    @ExceptionHandler(PaymentException.class)
    @ResponseBody
    public ResultVO exceptionHandlerByPaymentException(PaymentException e){
        return new ResultVO(false, ResultStatusEnum.ERROR.getCode(),e.getMessage());
    }
}
