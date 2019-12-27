package com.breeze.pojo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-06-30 21:39
 * @Description: 产品详情Dto
 */
public class ProductDetailsDTO {

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
    private List<ProductDTO> relatedProducts;

    /**
     * 商品图片URL
     */
    private List<String> productImage;

    /**
     * 商品属性
     */
    private ProductAttributesDTO productAttributes;



    public ProductDetailsDTO() {
    }

    public List<String> getProductImage() {
        return productImage;
    }

    public void setProductImage(List<String> productImage) {
        this.productImage = productImage;
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

    public List<ProductDTO> getRelatedProducts() {
        return relatedProducts;
    }

    public void setRelatedProducts(List<ProductDTO> relatedProducts) {
        this.relatedProducts = relatedProducts;
    }

    public ProductAttributesDTO getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(ProductAttributesDTO productAttributes) {
        this.productAttributes = productAttributes;
    }
}
