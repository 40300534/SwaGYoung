package com.breeze.pojo;

/**
 * @author 莫奇
 * 消息类型类
 */
public class MessageType {

    private Long id ;
    /**
     * 消息类型名称
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return "MessageType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
