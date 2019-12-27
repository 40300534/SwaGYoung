package com.breeze.controller;

import com.breeze.enums.ResultStatusEnum;
import com.breeze.pojo.Product;
import com.breeze.pojo.ProductImage;
import com.breeze.pojo.bo.NewProductRequestBO;
import com.breeze.pojo.bo.ProductBO;
import com.breeze.pojo.bo.ProductManageBO;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.INewProductRequestService;
import com.breeze.service.IProductImageService;
import com.breeze.service.IProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 莫奇
 */
@RestController
@RequestMapping(value = "/manage")
@Api(tags = "商品管理后台")
public class ProductManageController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductImageService productImageService;

    @Autowired
    private INewProductRequestService newProductRequestService;


    /**
     * 获取当前所有商品
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("获取所有产品")
    @GetMapping(value = "/findAllProduct")
    public PageInfo<ProductManageBO> findAllProduct(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(productService.findAllProduct());
    }

    /**
     * 修改商品
     * @param productImage
     * @return
     */
    @ApiOperation("修改商品")
    @PutMapping(value = "/updateProduct")
    public ResultVO update(@RequestBody ProductImage productImage){
        try {
            productImageService.update(productImage);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getName());
        }catch (Exception e){
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultStatusEnum.ERROR.getName());
        }
    }

    /**
     * 獲取全部新的商品審核信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("獲取全部新的商品審核信息")
    @GetMapping(value = "/findAllNewProductRequest")
    public PageInfo<NewProductRequestBO> findAllNewProductRequest(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(newProductRequestService.findAllNewProductRequest());
    }

    /**
     * 获取全部拒絕的商品審核信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("获取全部拒絕的商品審核信息")
    @GetMapping(value = "/findAllDeniedProductRequest")
    public PageInfo<NewProductRequestBO> findAllDeniedProductRequest(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(newProductRequestService.findAllDeniedProductRequest());
    }



}
