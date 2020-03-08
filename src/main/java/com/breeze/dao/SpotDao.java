package com.breeze.dao;

import com.breeze.pojo.Spot;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotDao extends JpaRepository<Spot,String>, JpaSpecificationExecutor<Spot> {


    //void del(@Param("ids") String ids); @Delete("DELETE  FROM spot WHERE id in(${ids})")
}
