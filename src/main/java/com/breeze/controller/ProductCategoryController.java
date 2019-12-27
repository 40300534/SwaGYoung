package com.breeze.controller;

import com.breeze.pojo.ProductCategory;
import com.breeze.service.IProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类管理
 * @author wdnnccey
 */
@RestController
@RequestMapping(value = "/category")
@Api(tags = {"商品分类管理"})
public class ProductCategoryController {

    @Autowired
    private IProductCategoryService productCategoryService;

    /**
     * 查询所有分类
     */
    @ApiOperation("查询所有分类")
    @GetMapping(value = "/findAll")
    public List<ProductCategory> findAll() {
        return productCategoryService.findAll();
    }

    /**
     * 添加分类
     */
    @ApiOperation("添加分类")
    @PostMapping()
    public int add(@RequestBody ProductCategory productCategory) {
        return productCategoryService.add(productCategory);
    }

    /**
     * 修改分类
     */
    @ApiOperation("修改分类")
    @PutMapping()
    public int update(@RequestBody ProductCategory productCategory) {
        return productCategoryService.update(productCategory);
    }

    /**
     * 删除分类
     */
    @ApiOperation("通过ID删除分类")
    @DeleteMapping(value ="/{id}")
    public int delete(@PathVariable("id") Integer id){
       return productCategoryService.deleteById(id);
    }


}
