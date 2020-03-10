package com.breeze.exception;

import org.springframework.stereotype.Component;

/***
 * 自定义异常
 */
@Component
public class MyException extends RuntimeException {


    public MyException(){

    }


    public MyException(String message) {
        super(message);
    }
}