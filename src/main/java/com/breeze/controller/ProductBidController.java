package com.breeze.controller;
import com.breeze.pojo.bo.UserBuyHistoryBO;
import com.breeze.pojo.bo.ProductBidBO;
import com.breeze.pojo.bo.ProductUserBidBO;
import com.breeze.service.IProductBidService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * @author tx
 */
@RestController
@RequestMapping(value = "/buyer")
@Api(tags = {"用户购买管理"})
public class ProductBidController {

    @Autowired
    private IProductBidService productBidService;
    /**
     * 获取所有出价
     */
    @ApiOperation("正在出价买的商品")
    @GetMapping
    public PageInfo<ProductBidBO> getBuyList(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(productBidService.findAll());
    }

    /**
     * 购买页的出价
     */
    @ApiOperation("购买页出价")
    @GetMapping(value = "/bid/{userId}")
    public PageInfo<ProductUserBidBO> getUserBuyList(@PathVariable("userId") Long userId,
                                                     @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(productBidService.findByUserId(userId));
    }
    /**
     * 购买页的历史
     */
    @ApiOperation("购买页历史")
    @GetMapping(value = "/history/{userId}")
    public PageInfo<UserBuyHistoryBO> findUserBuyHistory(@PathVariable("userId") Long userId,
                                                     @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
       return new PageInfo<>(productBidService.findUserBuyHistory(userId));
    }


}