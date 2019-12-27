package com.breeze.pojo.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Created by wdnnccey
 * @date 2019-07-01 15:13
 * @Description: 产品历史价格走势
 */
public class ProductHistoryPriceBO {

    /**
     * 产品成交时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd" , timezone="GMT+8")
    private Date createTime;

    /**
     * 产品价格
     */
    private BigDecimal price;

    public ProductHistoryPriceBO() {
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductHistoryPriceDto{" +
                "createTime=" + createTime +
                ", price=" + price +
                '}';
    }
}
