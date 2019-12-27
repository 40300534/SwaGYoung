package com.breeze.controller;

import com.breeze.constants.ResultMessage;
import com.breeze.enums.ResultStatusEnum;
import com.breeze.pojo.UserProductShoppingCart;
import com.breeze.pojo.bo.ShoppingCartBO;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.IShoppingCartService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author 莫奇
 * <p>
 * 购物车相关
 */
@RestController
@RequestMapping(value = "/cart")
@Api(tags = {"购物车管理"})
public class ShoppingCartController {

    @Autowired
    private IShoppingCartService shoppingCartService;


    /**
     * 添加商品到购物车
     *
     * @param quantity 数量
     * @return
     */
    //@LoginRequired
    @ApiOperation("添加商品到购物车")
    @PostMapping
    public ResultVO add(@RequestBody UserProductShoppingCart shoppingCart
            , @RequestParam(defaultValue = "1") Integer quantity) {
        shoppingCart.setQuantity(quantity);
        try {
            //保存数据到redis
            shoppingCartService.add(shoppingCart);

            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 删除购物车商品
     *
     * @param userId    用户Id
     * @param productId 商品Id
     * @return
     */
    //@LoginRequired
    @ApiOperation("删除购物车商品")
    @DeleteMapping
    public ResultVO deleteById(@RequestParam("productId") Long productId, @RequestParam("userId") Long userId) {
        UserProductShoppingCart shoppingCart = new UserProductShoppingCart();
        shoppingCart.setUserId(userId);
        shoppingCart.setProductId(productId);

        try {
            shoppingCartService.deleteById(shoppingCart);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 清空购物车
     *
     * @param userId
     * @return
     */
    //@LoginRequired
    @ApiOperation("清空购物车")
    @DeleteMapping(value = "/deleteAll")
    public ResultVO delete(@RequestParam("userId") Long userId) {
        try {
            shoppingCartService.delete(userId);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 查询购物车
     *
     * @param userId 用户Id
     * @return
     */
    //@LoginRequired
    @ApiOperation("获取当前用户的购物车")
    @GetMapping
    public PageInfo<ShoppingCartBO> getShoppingCartList(
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam("userId") Long userId) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(shoppingCartService.findAll(userId));
    }

    /**
     * 修改购物车商品数量
     *
     * @param shoppingCart
     * @return
     */
    //@LoginRequired
    @ApiOperation("修改购物车商品数量")
    @PutMapping()
    public ResultVO update(@RequestBody UserProductShoppingCart shoppingCart) {
        try {
            shoppingCartService.update(shoppingCart);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

}
