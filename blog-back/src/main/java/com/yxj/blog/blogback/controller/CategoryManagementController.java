package com.yxj.blog.blogback.controller;


import com.yxj.blog.blogback.entity.Result;
import com.yxj.blog.blogback.service.CategoryManagementService;
import com.yxj.blog.blogback.vo.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "分类管理接口", tags = "文章管理" )
@RestController
public class CategoryManagementController {

    @Resource
    private CategoryManagementService categoryManagementService;

    /**
     * 根据id删除分类的接口
     * @param categoryId
     * @return
     */
    @ApiOperation(value = "根据id删除分类的接口",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据id删除分类的接口的接口")
    @PostMapping("/removeCategoryById")
    public Result removeCategoryById(Long categoryId) {
        return categoryManagementService.removeCategoryById(categoryId);
    }

    /**
     * 分类管理
     * @return
     */
    @ApiOperation(value = "分类管理的接口",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;分类管理的接口")
    @PostMapping("/categoryManagement")
    public Result categoryManagement(@RequestBody PageVo pageVo) {
        return categoryManagementService.categoryManagement(pageVo);
    }



    /**
     * 根据分类名查询分类
     * @param name
     * @return
     */
    @ApiOperation(value = "根据分类名查询分类的接口",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据分类名查询分类的接口")
    @GetMapping("/selectCategoryByName")
    public Result selectCategoryByName(String name) {
        return categoryManagementService.selectCategoryByName(name);
    }
}
