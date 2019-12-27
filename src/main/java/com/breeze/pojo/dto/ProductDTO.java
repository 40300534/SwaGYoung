package com.breeze.pojo.dto;


import java.math.BigDecimal;

/**
 * @author wdnnccey
 * @date 2019-06-17 12:57
 * @description 产品展示]
 */
public class ProductDTO {
    /**
     * 产品id
     */
    private Long productId;

    /**
     * 产品分类名称
     */
    private String productCategoryName;

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

    public Long getProductId() {
        return productId;
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

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productId=" + productId +
                ", productCategoryName='" + productCategoryName + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
