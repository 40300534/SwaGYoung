package com.breeze.pojo.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 莫奇
 */
public class ProductManageBO {
    /**
     * 商品Id
     */
    private Long productId;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品最低卖家价格
     */
    private BigDecimal askMinPrice;

    /**
     * 商品最高買家出價
     */
    private BigDecimal bidMaxPrice;
    /**
     * 商品描述
     */
    private String description;
    /**
     *商品位置
     */
    private String location;
    /**
     * 商品状态
     */
    private Integer status;
    /**
     * 商品图片
     */
    private String imageUrl;
    /**
     *商品库存
     */
    private Integer inventory;
    /**
     * 商品创建时间
     */
    private Date createTime;
    /**
     * 商品修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date modifyTime;
    /**
     * 商品图片是否是主图
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Integer imageIsMaster;
    /**
     * 商品卖家费用
     */
    private Object askFee;
    /**
     * 商品卖家名字
     */
    private String askUserName;

    /**
     * 商品买家相关
     */
    private Map<String , BigDecimal> bidUserMap;
    /**
     * 商品品牌名称
     */
    private String brandName;
    /**
     * 商品产地名称
     */
    private String originName;
    /**
     * 商品分类名称
     */
    private String categoryName;
    /**
     * 商品收藏总人数
     */
    private Integer userFavorateCounts;

    @Override
    public String toString() {
        return "ProductManageBO{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", askMinPrice=" + askMinPrice +
                ", bidMaxPrice=" + bidMaxPrice +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", status=" + status +
                ", imageUrl='" + imageUrl + '\'' +
                ", inventory=" + inventory +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", imageIsMaster=" + imageIsMaster +
                ", askFee=" + askFee +
                ", askUserName='" + askUserName + '\'' +
                ", bidUserMap=" + bidUserMap +
                ", brandName='" + brandName + '\'' +
                ", originName='" + originName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", userFavorateCounts=" + userFavorateCounts +
                '}';
    }

    public BigDecimal getBidMaxPrice() {
        return bidMaxPrice;
    }

    public void setBidMaxPrice(BigDecimal bidMaxPrice) {
        this.bidMaxPrice = bidMaxPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAskMinPrice() {
        return askMinPrice;
    }

    public void setAskMinPrice(BigDecimal askMinPrice) {
        this.askMinPrice = askMinPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getImageIsMaster() {
        return imageIsMaster;
    }

    public void setImageIsMaster(Integer imageIsMaster) {
        this.imageIsMaster = imageIsMaster;
    }

    public Object getAskFee() {
        return askFee;
    }

    public void setAskFee(Object askFee) {
        this.askFee = askFee;
    }

    public String getAskUserName() {
        return askUserName;
    }

    public void setAskUserName(String askUserName) {
        this.askUserName = askUserName;
    }

    public Map<String, BigDecimal> getBidUserMap() {
        return bidUserMap;
    }

    public void setBidUserMap(Map<String, BigDecimal> bidUserMap) {
        this.bidUserMap = bidUserMap;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getUserFavorateCounts() {
        return userFavorateCounts;
    }

    public void setUserFavorateCounts(Integer userFavorateCounts) {
        this.userFavorateCounts = userFavorateCounts;
    }
}
