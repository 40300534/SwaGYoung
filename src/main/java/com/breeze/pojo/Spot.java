package com.breeze.pojo;

import com.breeze.pojo.dto.Pagedto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Spot  extends Pagedto {



    @Id
    private String id;


//    id	varchar	64	Y	主键
//    spot_name	varchar	64	N	景点名称
    private String spot_name;
//    spot _adder	varchar	64	N	景点地址
    private String spot_adder;
//    spot _details	varchar	64	N	商品详情
    private String spot_details;
//    Image	varchar	64	N	景点imageurl
    private String Image;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpot_name() {
        return spot_name;
    }

    public void setSpot_name(String spot_name) {
        this.spot_name = spot_name;
    }

    public String getSpot_adder() {
        return spot_adder;
    }

    public void setSpot_adder(String spot_adder) {
        this.spot_adder = spot_adder;
    }

    public String getSpot_details() {
        return spot_details;
    }

    public void setSpot_details(String spot_details) {
        this.spot_details = spot_details;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
