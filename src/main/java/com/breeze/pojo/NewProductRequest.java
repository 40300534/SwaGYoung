package com.breeze.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 莫奇
 */
public class NewProductRequest {
    private Long id;
    private Integer productCategoryId;
    private Integer productBrandId;
    private Integer productOriginId;
    private String name;
    private String description;
    private Integer statusId;
    private String comment;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
    private Date createTime;

    @Override
    public String toString() {
        return "NewProductRequest{" +
                "id=" + id +
                ", productCategoryId=" + productCategoryId +
                ", productBrandId=" + productBrandId +
                ", productOriginId=" + productOriginId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", statusId='" + statusId + '\'' +
                ", comment='" + comment + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Integer getProductBrandId() {
        return productBrandId;
    }

    public void setProductBrandId(Integer productBrandId) {
        this.productBrandId = productBrandId;
    }

    public Integer getProductOriginId() {
        return productOriginId;
    }

    public void setProductOriginId(Integer productOriginId) {
        this.productOriginId = productOriginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
