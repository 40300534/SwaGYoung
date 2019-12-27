package com.breeze.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 产品表
 *
 * @author wdnnccey
 */
public class Product {

    /**
     * 产品Id
     */
    private Long id;


    /**
     * 商品分类Id
     */
    private Integer productCategoryId;
    /**
     * 品牌Id
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer productBrandId;
    /**
     * 产地Id
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer productOriginId;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 图片地址
     */
    private String imageFolder;

    /**
     * 状态上架(1)或未上架(0)
     */
    private int status;

    /**
     * 商品位置
     */
    private String location;

    /**
     * 产品描述
     */
    private String description;
    /**
     * 上架时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date updateTime;

    public Product() {
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

    public String getImageFolder() {
        return imageFolder;
    }

    public void setImageFolder(String imageFolder) {
        this.imageFolder = imageFolder;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productCategoryId=" + productCategoryId +
                ", productBrandId=" + productBrandId +
                ", productOriginId=" + productOriginId +
                ", name='" + name + '\'' +
                ", imageFolder='" + imageFolder + '\'' +
                ", status=" + status +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
