package com.breeze.pojo.bo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-06-30 21:39
 * @Description: 产品详情Dto
 */
public class ProductDetailsBO {

    /**
     * 产品Id
     */
    private Long id;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 最后成交价格
     */
    private BigDecimal lastTransactionPrice;

    /**
     * 商品详情
     */
    private String description;

    /**
     * 商品价格（最低价格）
     */
    private BigDecimal price;

    /**
     * 商品最高出价
     */
    private BigDecimal maxPrice;

    /**
     * 相似商品
     */
    private List<ProductBO> relatedProducts;

    /**
     * 商品图片URL
     */
    private List<String> productImages;

    /**
     * 商品属性
     */
    private ProductAttributesBO productAttributes;

    /**
     * 商品库存
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer inventory;


    public ProductDetailsBO() {
    }

    public List<String> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<String> productImages) {
        this.productImages = productImages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getLastTransactionPrice() {
        return lastTransactionPrice;
    }

    public void setLastTransactionPrice(BigDecimal lastTransactionPrice) {
        this.lastTransactionPrice = lastTransactionPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<ProductBO> getRelatedProducts() {
        return relatedProducts;
    }

    public void setRelatedProducts(List<ProductBO> relatedProducts) {
        this.relatedProducts = relatedProducts;
    }

    public ProductAttributesBO getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(ProductAttributesBO productAttributes) {
        this.productAttributes = productAttributes;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
}
