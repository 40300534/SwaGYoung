package com.breeze.controller;

import com.breeze.annotation.LoginRequired;
import com.breeze.constants.ResultMessage;
import com.breeze.enums.ResultStatusEnum;
import com.breeze.pojo.NewsCategory;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.INewsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 莫奇
 * <p>
 * 文章分类相关
 */
@RestController
@RequestMapping(value = "/newsCategory")
@Api(tags = {"文章分类管理"})
public class NewsCategoryController {

    @Autowired
    private INewsCategoryService newsCategoryService;


    /**
     * 查询所有文章分类
     *
     * @return 文章分类
     */
    @ApiOperation("查询所有文章分类")
    @GetMapping(value = "/findAll")
    public List<NewsCategory> findAll() {
        List<NewsCategory> newsCategories = newsCategoryService.findAll();
        return newsCategories;
    }

    /**
     * 增加文章分类
     *
     * @param newsCategory 新增文章分类
     * @return
     */
    @ApiOperation("增加文章分类")
    @PostMapping()
    public ResultVO add(@RequestBody NewsCategory newsCategory) {
        try {
            newsCategoryService.add(newsCategory);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 删除文章分类
     *
     * @param id 文章分类Id
     * @return
     */
    @LoginRequired
    @ApiOperation("删除文章分类")
    @DeleteMapping(value = "/{id}")
    public ResultVO deleteById(@PathVariable("id") Integer id) {
        try {
            newsCategoryService.deleteById(id);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 修改文章分类
     *
     * @param newsCategory 文章分类
     * @return
     */
    @LoginRequired
    @ApiOperation("修改文章分类")
    @PutMapping()
    public ResultVO update(@RequestBody NewsCategory newsCategory) {
        try {
            newsCategoryService.update(newsCategory);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

}
