package com.breeze.pojo.bo;

import com.breeze.pojo.UserAddress;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-03 19:44
 * @Description: 订单预览封装
 */
public class OrderPreviewBO {

    /**
     * 商品信息
     * 包含最低售价（立即买）
     */
    private ProductBO productInfo;

    /**
     * 费用信息
     */
    private List<FeeBO> feeInfo;

    /**
     * 最高出价(出价买)
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal highestBidBuy;

    /**
     * 最低出价(立即卖)
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal lowestBidSell;

    /**
     * 最高出价(出价卖)
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal highestBidSell;


    /**
     * 用户地址（返回一个默认地址 is_default）
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserAddress userAddressInfo;

    /**
     * 计算一个总价返回给前端
     */
    private BigDecimal totalAmount;

    /**
     * 押金比例
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal deposit;

    /**
     * 当前登录的用户是否对商品有出价
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isBiding;

    /**
     * 当前用户出价中的价格
     * 每个用户只能对当前商品有一次出价，如果二次出价需要补充差价押金
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal biddingPrice;


    public OrderPreviewBO() {
    }

    public ProductBO getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductBO productInfo) {
        this.productInfo = productInfo;
    }

    public List<FeeBO> getFeeInfo() {
        return feeInfo;
    }

    public void setFeeInfo(List<FeeBO> feeInfo) {
        this.feeInfo = feeInfo;
    }

    public UserAddress getUserAddressInfo() {
        return userAddressInfo;
    }

    public void setUserAddressInfo(UserAddress userAddressInfo) {
        this.userAddressInfo = userAddressInfo;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getHighestBidBuy() {
        return highestBidBuy;
    }

    public void setHighestBidBuy(BigDecimal highestBidBuy) {
        this.highestBidBuy = highestBidBuy;
    }

    public BigDecimal getHighestBidSell() {
        return highestBidSell;
    }

    public void setHighestBidSell(BigDecimal highestBidSell) {
        this.highestBidSell = highestBidSell;
    }

    public BigDecimal getLowestBidSell() {
        return lowestBidSell;
    }

    public void setLowestBidSell(BigDecimal lowestBidSell) {
        this.lowestBidSell = lowestBidSell;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public Boolean getBiding() {
        return isBiding;
    }

    public void setBiding(Boolean biding) {
        isBiding = biding;
    }

    public BigDecimal getBiddingPrice() {
        return biddingPrice;
    }

    public void setBiddingPrice(BigDecimal biddingPrice) {
        this.biddingPrice = biddingPrice;
    }
}
