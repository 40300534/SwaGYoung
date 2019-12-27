package com.breeze.pojo;

/**
 * @author TianXu
 * @create 2019/8/2  11:53
 * @Description Portfolio
 */
public class UserProductPortfolio {
    /**
     * id
     */
    private Long id;
    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 商品Id
     */
    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "UserProductPortfolio{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
