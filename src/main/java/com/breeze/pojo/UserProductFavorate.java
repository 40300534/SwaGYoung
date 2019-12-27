package com.breeze.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author wdnnccey 莫奇
 * @date 2019-07-01
 * @Description: 用户收藏类
 */
public class UserProductFavorate {


        private Long id;

        private Long productId;

        private Long userId;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date createTime;

        public UserProductFavorate() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        @Override
        public String toString() {
            return "ProductUserFavorate{" +
                    "id=" + id +
                    ", productId=" + productId +
                    ", userId=" + userId +
                    ", createTime=" + createTime +
                    '}';
        }
    }

