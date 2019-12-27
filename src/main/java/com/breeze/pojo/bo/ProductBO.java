package com.breeze.pojo.bo;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wdnnccey
 * @date 2019-06-17 12:57
 * @description 产品展示]
 */
public class ProductBO {
    /**
     * 产品id
     */
    private Long productId;

    /**
     * 产品分类名称
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String productCategoryName;

    /**
     * 产品描述
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 主图地址(在首页显示)
     */
    private String masterPicture;

    /**
     * 卖家Id
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long sellUserId;

    /**
     * 卖家上架的押金交易id
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long sellTransactionId;

    /**
     * 买家Id
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long buyerUserId;

    /**
     * 买家出价的押金交易Id
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long buyerTransactionId;

    /**
     * 商品库存
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer inventory;

    /**
     * 时间
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date createTime;

    public Long getProductId() {
        return productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMasterPicture() {
        return masterPicture;
    }

    public void setMasterPicture(String masterPicture) {
        this.masterPicture = masterPicture;
    }

    public Long getSellUserId() {
        return sellUserId;
    }

    public void setSellUserId(Long sellUserId) {
        this.sellUserId = sellUserId;
    }

    public Long getSellTransactionId() {
        return sellTransactionId;
    }

    public void setSellTransactionId(Long sellTransactionId) {
        this.sellTransactionId = sellTransactionId;
    }

    public Long getBuyerUserId() {
        return buyerUserId;
    }

    public void setBuyerUserId(Long buyerUserId) {
        this.buyerUserId = buyerUserId;
    }

    public Long getBuyerTransactionId() {
        return buyerTransactionId;
    }

    public void setBuyerTransactionId(Long buyerTransactionId) {
        this.buyerTransactionId = buyerTransactionId;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
