package com.breeze.pojo;

import java.util.Date;

/**
 * @author Created by wdnnccey
 * @date 2019-06-26 00:23
 * @Description: 一句话描述该类的功能
 */
public class ProductImage {

    /**
     * id
     */
    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 描述
     */
    private String description;

    /**
     * 图片地址
     */
    private String url;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 是否是主图？
     */
    private Integer isMaster;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getIsMaster() {
        return isMaster;
    }

    public void setIsMaster(Integer isMaster) {
        this.isMaster = isMaster;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}