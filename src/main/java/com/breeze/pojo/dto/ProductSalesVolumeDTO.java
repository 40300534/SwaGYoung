package com.breeze.pojo.dto;

import java.util.Date;

/**
 * @author wdnnccey
 * @date 2019-06-25 15:25
 * @Description: 产品销量Dto
 */
public class ProductSalesVolumeDTO {

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 产品销量
     */
    private String quantity;

    /**
     * 销售日期
     */
    private Date createTime;


    public ProductSalesVolumeDTO() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ProductSalesVolumeDto{" +
                "productId=" + productId +
                ", quantity='" + quantity + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
