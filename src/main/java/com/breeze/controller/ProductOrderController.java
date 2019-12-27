package com.breeze.controller;

import com.breeze.service.IProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "")
public class ProductOrderController {

    @Autowired
    private IProductOrderService productOrderService;
}

