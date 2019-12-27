package com.breeze.pojo;

/**
 * @author TianXu
 * @create 2019/8/2  14:47
 * @Description TODO
 */
public class OrderTrackingStatusType {
    /**
     * Id
     */
    private Integer id;
    /**
     * 订单—运单-状态类型名
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrderTrackingStatusType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
