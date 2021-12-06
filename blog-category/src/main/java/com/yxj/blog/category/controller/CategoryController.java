
package com.yxj.blog.category.controller;

import com.yxj.blog.category.service.CategoryService;
import com.yxj.blog.common.vo.CategoryVo;
import com.yxj.blog.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 从前慢
 * @Slf4j 生成日志
 * @RestController 返回json数据
 * @Resource 生成的类实例交给SpringIOC容器管理
 * @GetMapping Get请求
 * @Api swagger类上接口说明
 * @ApiOperation swagger方法上接口说明
 */
@Slf4j
@RestController
@Api(value = "类别接口", tags = "类别管理" )
public class CategoryController {

    @Resource
    CategoryService categoryService;

    /**
     * 根据类别id查询类别信息
     * @param categoryId
     * @return
     */
    @GetMapping("/categorys/findCategoryById")
    @ApiOperation(value = "根据类别id查询类别信息",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据类别id查询类别信息的接口")
    public CategoryVo findCategoryById(Long categoryId){
        return categoryService.findCategoryById(categoryId);

    }

    @GetMapping("/categorys")
    @ApiOperation(value = "查询所有分类",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;查询所有分类的接口")
    public Result categories(){
        return categoryService.findAll();
    }

    @ApiOperation(value = "查询所有分类详细内容",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;查询所有分类详细内容的接口")
    @GetMapping("/categorys/detail")
    public Result categoriesDetail() {
        return categoryService.findAllDetail();
    }

    @ApiOperation(value = "根据分类id查询分类详细信息",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据分类id查询分类详细信息的接口")
    @GetMapping("/categorys/detail/{id}")
    public Result categoryDetailById(@PathVariable("id") Long id){
        return categoryService.categoryDetailById(id);
    }
}
