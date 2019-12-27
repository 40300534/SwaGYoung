package com.breeze.pojo;

/**
 * @author TianXu
 * @create 2019/8/2  11:47
 * @Description UserActivityType
 */
public class UserActivityType {
    /**
     * Id
     */
    private Integer id;
    /**
     * 姓名
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
        return "UserActivityType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
