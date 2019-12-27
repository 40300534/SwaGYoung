package com.breeze.controller;

import com.breeze.constants.ResultMessage;
import com.breeze.enums.ResultStatusEnum;
import com.breeze.pojo.Product;
import com.breeze.pojo.bo.ProductBO;
import com.breeze.pojo.bo.ProductDetailsBO;
import com.breeze.pojo.bo.ProductHistoryPriceBO;
import com.breeze.pojo.bo.ProductSalesVolumeBO;
import com.breeze.pojo.dto.ProductDTO;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.IProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 产品相关
 *
 * @author wdnnccey
 */
@RestController
@RequestMapping(value = "/products")
@Api(tags = {"商品管理"})
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 获取所有产品
     */
    @ApiOperation("获取所有产品")
    @GetMapping(value = "/findAll")
    public PageInfo<ProductBO> getProductList(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                              @RequestParam(name = "sort", required = false) String sort,
                                              @RequestParam(name = "order", required = false, defaultValue = "desc") String order) {
        if (StringUtils.isNotBlank(sort)) {
            PageHelper.startPage(pageNum, pageSize);
        } else {
            PageHelper.startPage(pageNum, pageSize, sort +" "+ order);
        }
        return new PageInfo<>(productService.findAll());

    }

    /**
     * 通过商品ID查询商品
     */
    @ApiOperation("通过商品ID查询商品")
    @GetMapping(value = "/{id}")
    public Product findById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    /**
     * 通过分类Id查询商品
     */
    @ApiOperation("通过分类Id查询商品")
    @GetMapping(value = "/category/{id}")
    public PageInfo<ProductBO> findByCategoryId(@PathVariable("id") Integer id,
                                                @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(productService.findByCategoryId(id));
    }

    /**
     * 通过关键词查询 , 模糊查询
     */
    @ApiOperation("查询商品")
    @GetMapping(value = "/search")
    public PageInfo<ProductBO> findByName(@RequestParam(value = "keyword", required = false) String name,
                                          @RequestParam(value = "cid", required = false, defaultValue = "") Integer cid,
                                          @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(productService.findByNameAndCategoryId(name, cid));
    }

    /**
     * 商品详情
     */
    @ApiOperation("商品详情")
    @GetMapping(value = "/details/{id}")
    public ProductDetailsBO productDetails(@PathVariable(name = "id") Long id) {
        return productService.findProductDetailsById(id);
    }

    /**
     * 最新的10款产品
     */
    @ApiOperation("最新的商品")
    @GetMapping(value = "/top/new")
    public PageInfo<ProductBO> newestProductRankingTop(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(productService.newestProduct());
    }


    /**
     * 最近销售的产品（刚刚销售的 看产品卖出时间计算）
     */
    @ApiOperation("最近销售的产品")
    @GetMapping(value = "/top/sale")
    public PageInfo<ProductBO> salesVolumeTop(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(productService.recentlySoldProducts());
    }


    /**
     * 近30天销量最大的产品(看产品出售总数计算)
     */
    @ApiOperation("最近一个月内销量最多的产品")
    @GetMapping(value = "/top/bestSale")
    public PageInfo<ProductBO> salesVolumeBestTop(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductSalesVolumeBO> productSalesVolumeTop = productService.findProductSalesVolumeTop();
        if (productSalesVolumeTop.size() == 0) {
            return null;
        }
        List<ProductBO> productSalesVolumeTopById = productService.findProductInId(productSalesVolumeTop
                .stream()
                .map(ProductSalesVolumeBO::getProductId)
                .collect(Collectors.toList()));
        return new PageInfo<>(productSalesVolumeTopById);
    }

    /**
     * 添加商品
     */
    @ApiOperation("添加商品")
    @PostMapping()
    public ResultVO add(@RequestBody Product product) {

        try {
            productService.add(product, null);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 删除商品
     */
    @ApiOperation("删除商品")
    @DeleteMapping(value = "/{id}")
    public int delete(@PathVariable("id") Long id) {
        return productService.deleteById(id);
    }

    /**
     * 修改商品
     */
    @ApiOperation("修改商品")
    @PutMapping()
    public int update(@RequestBody Product product) {
        return productService.update(product);
    }

    @ApiOperation("查询时键入提示返回")
    @GetMapping(value = "/searchByWord")
    public List<Product> queryPrompt(@RequestParam(name = "keyword") String keyword) {
        return productService.queryPrompt(keyword);
    }

    /**
     * 查找产品历史成绩价走势
     * @param productId
     * @return
     */
    @ApiOperation("查询产品历史成交价走势")
    @GetMapping("/history")
    public List<ProductHistoryPriceBO> findProductHistory(@RequestParam(name = "productId") Long productId) {
        return productService.findProductHistoryList(productId);
    }

}
