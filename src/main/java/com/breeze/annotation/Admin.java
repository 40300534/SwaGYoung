package com.breeze.annotation;

import java.lang.annotation.*;

/**
 * @author 田旭
 * @date 2019/6/26 9:37
 */
// 本注解只能用在方法上
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Admin {
}
