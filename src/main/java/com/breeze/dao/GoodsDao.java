package com.breeze.dao;

import com.breeze.pojo.Goods;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsDao extends JpaRepository<Goods,String> {
}
