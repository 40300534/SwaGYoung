package com.breeze.pojo.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author TianXu
 * @create 2019/7/30  15:32
 * @Description TODO
 */
public class OrderBO {
    /**
     * 订单Id
     */
    private Long orderId;
    /**
     * 买家Id
     */
    private Long buyerId;
    /**
     * 买家名
     */
    private String buyerName;
    /**
     * 卖家Id
     */
    private Long sellerId;
    /**
     * 卖家名
     */
    private String sellerName;
    /**
     * 商品Id
     */
    private Long productId;
    /**
     * 购买商品
     */
    private String productName;
    /**
     * 商品图
     */
    private String masterPicture;
    /**
     * 总价
     */
    private BigDecimal totalPrices;
    /**
     * 状态Id
     */
    private Long statusId;
    /**
     * 当前状态
     */
    private String orderStatus;
    /**
     * 状态更新日期 TODO
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date statusUpdateTime;
    /**
     * 押金付款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date depositPaymentTime;
    /**
     * 付全额时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date totalPaymentTime;
    /**
     * 保证金 (买家)
     */
    private BigDecimal buyerDeposit;
    /**
     * 保证金(卖家)
     */
    private BigDecimal sellerDeposit;
    /**
     * 退卖家保证金，退买家保证金，退全款 TODO
     */
    private String drawBack;
    /**
     * 物流信息 TODO
     */
    private String logisticsInformation;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public BigDecimal getTotalPrices() {
        return totalPrices;
    }

    public void setTotalPrices(BigDecimal totalPrices) {
        this.totalPrices = totalPrices;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getStatusUpdateTime() {
        return statusUpdateTime;
    }

    public void setStatusUpdateTime(Date statusUpdateTime) {
        this.statusUpdateTime = statusUpdateTime;
    }

    public Date getDepositPaymentTime() {
        return depositPaymentTime;
    }

    public void setDepositPaymentTime(Date depositPaymentTime) {
        this.depositPaymentTime = depositPaymentTime;
    }

    public Date getTotalPaymentTime() {
        return totalPaymentTime;
    }

    public void setTotalPaymentTime(Date totalPaymentTime) {
        this.totalPaymentTime = totalPaymentTime;
    }

    public BigDecimal getBuyerDeposit() {
        return buyerDeposit;
    }

    public void setBuyerDeposit(BigDecimal buyerDeposit) {
        this.buyerDeposit = buyerDeposit;
    }

    public BigDecimal getSellerDeposit() {
        return sellerDeposit;
    }

    public void setSellerDeposit(BigDecimal sellerDeposit) {
        this.sellerDeposit = sellerDeposit;
    }

    public String getDrawBack() {
        return drawBack;
    }

    public void setDrawBack(String drawBack) {
        this.drawBack = drawBack;
    }

    public String getLogisticsInformation() {
        return logisticsInformation;
    }

    public void setLogisticsInformation(String logisticsInformation) {
        this.logisticsInformation = logisticsInformation;
    }
}
