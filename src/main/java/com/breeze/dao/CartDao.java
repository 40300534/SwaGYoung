package com.breeze.dao;

import com.breeze.pojo.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends JpaRepository<Cart,String>, JpaSpecificationExecutor<Cart> {
}
