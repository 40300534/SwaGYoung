package com.breeze.pojo.bo;


/**
 * @author Created by wdnnccey
 * @date 2019-06-26 14:57
 * @Description: 产品图片相关信息封装
 */
public class ProductImageInfoBO {

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 产品图片id
     */
    private Long productImageId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品目录（前缀）
     */
    //TODO
    private String imageFolder;

    /**
     * 产品目录（后缀）
     */
    private String productImageUrl;


    public ProductImageInfoBO() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(Long productImageId) {
        this.productImageId = productImageId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageFolder() {
        return imageFolder;
    }

    public void setImageFolder(String imageFolder) {
        this.imageFolder = imageFolder;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    @Override
    public String toString() {
        return "ProductImageInfoDto{" +
                "productId=" + productId +
                ", productImageId=" + productImageId +
                ", productName='" + productName + '\'' +
                ", imageFolder='" + imageFolder + '\'' +
                ", productImageUrl='" + productImageUrl + '\'' +
                '}';
    }
}
