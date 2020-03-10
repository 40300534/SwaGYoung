package com.breeze.mapper;

import com.breeze.pojo.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {

    @Select("select * from goods where id=#{id}")
    Goods findById(@Param("id") String id);

    List<Goods> search(@Param("goods") Goods goods);
}
