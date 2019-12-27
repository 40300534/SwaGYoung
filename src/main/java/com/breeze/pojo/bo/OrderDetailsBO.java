package com.breeze.pojo.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author TianXu
 * @create 2019/7/22  13:13
 * @Description 订单的详情页
 */
public class OrderDetailsBO {
    /**
     * 订单编号
     */
    private Long orderId;
    /**
     * productId商品ID
     */
    private Long productId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户人手机号码
     */
    private String userPhone;
    /**
     * 用户地址
     */
    private String userAddress;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品图片
     */
    private String masterPicture;
    /**
     * 商品总价
     */
    private BigDecimal productTotalPrice;
    /**
     * 运费
     */
    private Object fee;
    /**
     * 订单总价
     */
    private BigDecimal orderTotalPrice;
    /**
     * 实际付款
     */
    private BigDecimal actualPayment;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
    /**
     * 付款时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date paymentTime;
    /**
     * 发货时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date shipmentsTime;

    @Override
    public String toString() {
        return "OrderDetailsBO{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", productName='" + productName + '\'' +
                ", masterPicture='" + masterPicture + '\'' +
                ", productTotalPrice=" + productTotalPrice +
                ", fee=" + fee +
                ", orderTotalPrice=" + orderTotalPrice +
                ", actualPayment=" + actualPayment +
                ", createTime=" + createTime +
                ", paymentTime=" + paymentTime +
                ", shipmentsTime=" + shipmentsTime +
                '}';
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMasterPicture() {
        return masterPicture;
    }

    public void setMasterPicture(String masterPicture) {
        this.masterPicture = masterPicture;
    }

    public BigDecimal getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(BigDecimal productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public Object getFee() {
        return fee;
    }

    public void setFee(Object fee) {
        this.fee = fee;
    }

    public BigDecimal getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public BigDecimal getActualPayment() {
        return actualPayment;
    }

    public void setActualPayment(BigDecimal actualPayment) {
        this.actualPayment = actualPayment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getShipmentsTime() {
        return shipmentsTime;
    }

    public void setShipmentsTime(Date shipmentsTime) {
        this.shipmentsTime = shipmentsTime;
    }

}
