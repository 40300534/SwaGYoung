package com.breeze.pojo;

import java.util.Date;

/**
 * @author Created by wdnnccey
 * @date 2019-07-25 10:21
 * @Description: 意见反馈类型
 */
public class FeedbackType {
    /**
    * id
    */
    private Integer id;

    /**
    * 类型名称
    */
    private String name;

    /**
    * 创建时间
    */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}