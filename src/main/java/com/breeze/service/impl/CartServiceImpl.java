package com.breeze.service.impl;

import com.breeze.dao.CartDao;
import com.breeze.pojo.Cart;
import com.breeze.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;
    @Override
    public void addCart(Cart cart) {
        cartDao.save(cart);
    }

    @Override
    public Page<Cart> searchCart(Cart cart) {

        Specification specification = (Specification) (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> predicateArrayList = new ArrayList<>();

            if(!StringUtils.isEmpty(cart.getRelevance_name())){
                Predicate p1 = criteriaBuilder.like(root.get("relevance_name"), "%" + cart.getRelevance_name() + "%");

                predicateArrayList.add(p1);
            }
            if(!StringUtils.isEmpty(cart.getPrice())){
                Predicate p2 = criteriaBuilder.like(root.get("price"), cart.getPrice());

                predicateArrayList.add(p2);
            }
            return criteriaBuilder.and(predicateArrayList.toArray(new Predicate[predicateArrayList.size()]));
        };

        Pageable Pageable = PageRequest.of(cart.getPageNum(), cart.getPageSize());
        Page all = cartDao.findAll(specification, Pageable);
        return all;


    }
}
