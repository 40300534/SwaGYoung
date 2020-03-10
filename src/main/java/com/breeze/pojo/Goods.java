package com.breeze.pojo;


import com.breeze.pojo.dto.Pagedto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Goods extends Pagedto {


    @Id
    private String  id;
//    id	varchar	64	Y	主键
//    goods_name	varchar	64	N
    @Column(length = 64)
    private String goods_name;
//    spot _id	varchar	64	N	景点id
    @Column(length = 64)
    private String  spot_id;
//    goods_price	varchar	64	N	商品价格
    @Column(length = 64)
    private String goods_price;
//    create_time	timestamp	64	N	创建时间

    private String goods_url;

    @Column(length = 64)
    private String create_time;

    public String getGoods_url() {
        return goods_url;
    }

    public void setGoods_url(String goods_url) {
        this.goods_url = goods_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getSpot_id() {
        return spot_id;
    }

    public void setSpot_id(String spot_id) {
        this.spot_id = spot_id;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
