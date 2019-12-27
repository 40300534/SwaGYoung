package com.breeze.pojo.dto;

import com.breeze.pojo.UserAddress;
import com.breeze.pojo.bo.FeeBO;
import com.breeze.pojo.bo.ProductBO;

/**
 * @author Created by wdnnccey
 * @date 2019-07-03 20:02:29
 * @Description: 订单预览封装
 */
public class OrderPreviewDTO {


    //产品id
    //产品主图
    //产品标题
    //立即购买（当前商品最低价格）
    //计算产品费用（需要）

    //用户地址

    //提交订单

    /**
     * 商品信息
     */
    private ProductBO productInfo;

    /**
     * 费用信息
     */
    private FeeBO feeInfo;

    /**
     * 用户地址（返回一个默认地址 is_default）
     */
    private UserAddress userAddressInfo;

    public OrderPreviewDTO() {
    }

    public ProductBO getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductBO productInfo) {
        this.productInfo = productInfo;
    }

    public FeeBO getFeeInfo() {
        return feeInfo;
    }

    public void setFeeInfo(FeeBO feeInfo) {
        this.feeInfo = feeInfo;
    }

    public UserAddress getUserAddressInfo() {
        return userAddressInfo;
    }

    public void setUserAddressInfo(UserAddress userAddressInfo) {
        this.userAddressInfo = userAddressInfo;
    }
}
