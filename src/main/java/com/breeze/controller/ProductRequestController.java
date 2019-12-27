package com.breeze.controller;

import com.breeze.enums.ResultStatusEnum;
import com.breeze.pojo.*;
import com.breeze.pojo.bo.NewProductRequestBO;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 莫奇
 */
@RestController
@RequestMapping(value = "/productRequest")
@Api(tags = "商品申请管理")
public class ProductRequestController {

    @Autowired
    private IProductService productService;

    @Autowired
    private INewProductRequestService newProductRequestService;

    @Autowired
    private IProductCategoryService productCategoryService;

    @Autowired
    private IBrandService brandService;

    @Autowired
    private IOriginService originService;

    /**
     * 获取全部商品分类
     *
     * @return
     */
    @ApiOperation("获取全部商品分类")
    @GetMapping(value = "/findAllProductCategory")
    public List<ProductCategory> findAllProductCategory() {
        return productCategoryService.findAll();
    }

    /**
     * 获取全部商品品牌
     *
     * @return
     */
    @ApiOperation("获取全部商品品牌")
    @GetMapping(value = "/findAllBrand")
    public List<ProductBrand> findAllBrand() {
        return brandService.findAll();
    }

    /**
     * 获取全部商品产地
     *
     * @return
     */
    @ApiOperation("获取全部商品产地")
    @GetMapping(value = "findAllOrigin")
    public List<ProductOrigin> findAllOrigin() {
        return originService.findAll();
    }

    /**
     * 申请新增商品
     *
     * @param newProductRequestBO
     * @return
     */
    @ApiOperation("申请新增商品")
    @ResponseBody
    @PostMapping
    public ResultVO add(@RequestBody NewProductRequestBO newProductRequestBO) {
        try {
            newProductRequestService.add(newProductRequestBO);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getName(),newProductRequestBO.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultStatusEnum.ERROR.getName());
        }

    }

    /**
     * 获取正在审核的商品信息
     *
     * @param id
     * @return
     */
    @ApiOperation("获取正在审核的商品信息")
    @GetMapping
    public NewProductRequestBO findProductsByUserId(@RequestParam(name = "id") Long id) {
        return newProductRequestService.findById(id);
    }

    /**
     * 刪除正在审核的商品
     *
     * @param id
     * @return
     */
    @ApiOperation("刪除正在审核的商品")
    @DeleteMapping(value = "/{id}")
    public ResultVO deleteById(@PathVariable(name = "id") Long id) {
        try {
            newProductRequestService.deleteById(id);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultStatusEnum.ERROR.getName());
        }
    }

    /**
     * 是否通过审核(新增商品是否成功)
     *
     * @return
     */
    @ApiOperation("是否通过审核(新增商品是否成功)")
    @GetMapping(value = "/isRequestSuccess")
    public boolean isRequestSuccess(@RequestParam(name = "id") Long id) {
        return newProductRequestService.isRequestSuccess(id);

    }


}
