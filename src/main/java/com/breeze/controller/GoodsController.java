package com.breeze.controller;

import com.breeze.pojo.Goods;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.GoodsService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value = "/goods")
@Api(value = "商品",description ="商品" )
public class GoodsController {

    //旅游商品检索、浏览模块

    @Autowired
    private GoodsService goodsService;



    @RequestMapping(value = "/search",method = RequestMethod.POST)
    @ApiOperation("商品检索")
    public ResultVO search(@RequestBody Goods goods){

       PageInfo<Goods> goodsList = goodsService.search(goods);
       return new ResultVO(true,200,"成功",goodsList);
   }

   @RequestMapping(value = "/saveGoods",method = RequestMethod.POST)
   @ApiOperation("添加商品")
   public ResultVO saveGoods(@RequestBody  Goods goods){
       goodsService.save(goods);
       return new ResultVO(true,200,"成功");
   }
    @RequestMapping(value = "/updateGoods",method = RequestMethod.POST)
    @ApiOperation("修改商品")
    public ResultVO updateGoods(@RequestBody  Goods goods){
        goodsService.update(goods);
        return new ResultVO(true,200,"成功");
    }



    @RequestMapping(value = "/findById/{id}",method = RequestMethod.GET)
    @ApiOperation("商品检索")
    public ResultVO findById(@PathVariable("id") String id){
        Goods goods = goodsService.findById(id);
        return new ResultVO(true,200,"成功",goods);
    }


    @RequestMapping(value = "/addCart",method = RequestMethod.POST)
    @ApiOperation("添加购物车")
    public ResultVO addCart(@RequestBody Goods goods){
        goodsService.addCart(goods);
        return new ResultVO(true,200,"成功",goods);
    }



}
