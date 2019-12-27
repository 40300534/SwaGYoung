package com.breeze.controller;

import com.breeze.annotation.LoginRequired;
import com.breeze.constants.ResultMessage;
import com.breeze.enums.ResultStatusEnum;
import com.breeze.pojo.News;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.INewsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 莫奇
 * <p>
 * 文章相关
 */
@RestController
@RequestMapping(value = "/news")
@Api(tags = {"文章管理"})
public class NewsController {

    @Autowired
    private INewsService newsService;

    /**
     * 文章全部展示
     *
     * @return 文章详情
     */
    @ApiOperation("查询全部文章")
    @GetMapping(value = "/findAll")
    public PageInfo<News> findAll(
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(newsService.findAll());
    }

    /**
     * 根据文章id查询文章
     *
     * @param newsId 文章Id
     * @return
     */
    @ApiOperation("根据文章id查询文章")
    @GetMapping
    public News findById(@RequestParam("newsId") Long newsId) {

        return newsService.findById(newsId);
    }

    /**
     * 根据文章分类Id查询文章
     *
     * @param categoryId 文章分类Id
     * @return
     */
    @ApiOperation("根据文章分类Id查询文章")
    @GetMapping(value = "/Category")
    public PageInfo<News> findByCategoryId(
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam("categoryId") Integer categoryId) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(newsService.findByCategoryId(categoryId));
    }

    /**
     * 增加文章
     *
     * @param news 新增文章
     * @return
     */
    @ApiOperation("增加文章")
    @PostMapping()
    public ResultVO add(@RequestBody News news) {
        try {
            newsService.add(news);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }

    }

    /**
     * 更新文章
     *
     * @param news 问章
     * @return
     */
    @LoginRequired
    @ApiOperation("更新文章")
    @PutMapping()
    public ResultVO update(@RequestBody News news) {

        try {
            newsService.update(news);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 删除文章
     *
     * @param id 文章Id
     * @return
     */
    @LoginRequired
    @ApiOperation("删除文章")
    @DeleteMapping(value = "/{id}")
    public ResultVO deleteById(@PathVariable("id") Long id) {

        try {
            newsService.deleteById(id);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }
}
