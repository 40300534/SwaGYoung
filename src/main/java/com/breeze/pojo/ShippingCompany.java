package com.breeze.pojo;

/**
 * @author TianXu
 * @create 2019/8/2  13:17
 * @Description TODO
 */
public class ShippingCompany {
    /**
     * Id
     */
    private Integer id;
    /**
     * 快递公司名称
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
        return "ShippingCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
