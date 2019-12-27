package com.breeze.pojo.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tx
 * @program: tensquare-72
 * @description: CRUD执行结果
 **/
@Getter
@Setter
public class ResultVO {
    /**
     * 成功标记1
     */
    private boolean flag;
    /**
     * 业务功能状态码 成功：200  失败：500
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 查询返回数据
     */
    private Object data;

    public ResultVO() {
    }

    public ResultVO(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public ResultVO(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
