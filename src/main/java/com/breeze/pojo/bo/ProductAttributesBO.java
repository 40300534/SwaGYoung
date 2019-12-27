package com.breeze.pojo.bo;


import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-01 10:08
 * @Description: 商品属性
 */
public class ProductAttributesBO {

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌详情
     */
    private String desc;

    /**
     * 产地名称
     */
    private String originName;

    /**
     * 产品特有属性
     */
    private List<ProductUniqueAttributeBO> attributes;

    public ProductAttributesBO() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public List<ProductUniqueAttributeBO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ProductUniqueAttributeBO> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "ProductAttributesDto{" +
                "productId=" + productId +
                ", brandName='" + brandName + '\'' +
                ", desc='" + desc + '\'' +
                ", originName='" + originName + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
