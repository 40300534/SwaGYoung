package com.breeze.controller;
import com.breeze.pojo.Cart;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cart")
@CrossOrigin
@Api(value = "购物车",description = "购物车")
public class CartController {

    @Autowired
    private CartService cartService;


    @RequestMapping(value = "/searchCart",method = RequestMethod.POST)
    @ApiOperation("查询购物车")
    public ResultVO searchCart(@RequestBody Cart cart){
        Page<Cart> page = cartService.searchCart(cart);
        return new ResultVO(true,200,"成功",page);
    }





}
