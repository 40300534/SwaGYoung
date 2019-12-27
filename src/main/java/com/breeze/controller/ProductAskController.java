package com.breeze.controller;
import com.breeze.pojo.bo.UserSellHistoryBO;
import com.breeze.pojo.bo.ProductAskBO;
import com.breeze.pojo.bo.ProductUserAskBO;
import com.breeze.service.IProductAskService;
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
@RequestMapping(value = "/seller")
@Api(tags = {"用户出售管理"})
public class ProductAskController {

    @Autowired
    private IProductAskService productAskService;
    /**
     * 获取所有用户出售商品
     */
    @ApiOperation("正在出价卖的商品")
    @GetMapping
    public PageInfo<ProductAskBO> getBuyList(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(productAskService.findAll());
    }

    /**
     * 获取用户出售商品
     */
    @ApiOperation("出售页出售中")
    @GetMapping(value = "/sell/{userId}")
    public PageInfo<ProductUserAskBO> getUserAskList(@PathVariable("userId") Long userId,
                                                     @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(productAskService.findByUserId(userId));
    }
    /**
     * 用户为卖家出售历史
     */
    @ApiOperation("出售页历史")
    @GetMapping(value = "/history/{userId}")
    public PageInfo<UserSellHistoryBO> findUserSellHistory(@PathVariable("userId") Long userId,
                                                       @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(productAskService.findUserSellHistory(userId));
    }

}