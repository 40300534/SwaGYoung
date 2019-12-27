package com.breeze.pojo;

/**
 * 文章分类表
 * @author wdnnccey
 */
public class NewsCategory {

    /**
     * 文章类别Id
     */
    private Integer id;

    /**
     * 文章类别名字
     */
    private String name;

    public NewsCategory() {
    }

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
        return "NewsCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
