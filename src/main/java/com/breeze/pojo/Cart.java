package com.breeze.pojo;

import com.breeze.pojo.dto.Pagedto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cart extends Pagedto {


//    id	varchar	64	Y	主键
    @Id
    private String id;
//    user_id	varchar	64	N	用户id
    private String user_id;
//    relevance_id	varchar	64	N	景点/商品id
    private String relevance_id;

    private String relevance_name; //商品名称

    private String price;


    public String getRelevance_name() {
        return relevance_name;
    }

    public void setRelevance_name(String relevance_name) {
        this.relevance_name = relevance_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRelevance_id() {
        return relevance_id;
    }

    public void setRelevance_id(String relevance_id) {
        this.relevance_id = relevance_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
