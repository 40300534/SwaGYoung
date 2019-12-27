package com.breeze.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author TianXu
 * @create 2019/8/2  11:45
 * @Description UserActivity
 */
public class UserActivity {
    /**
     * Id
     */
    private Long id;
    /**
     * 类型
     */
    private Integer typeId;
    /**
     * 创建时间
     *
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm" , timezone="GMT+8")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserActivity{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", createTime=" + createTime +
                '}';
    }
}
