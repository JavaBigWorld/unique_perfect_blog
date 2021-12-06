package com.yxj.blog.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yxj.blog.category.mapper.CategoryMapper;
import com.yxj.blog.category.service.CategoryService;
import com.yxj.blog.common.entity.Category;
import com.yxj.blog.common.vo.CategoryVo;
import com.yxj.blog.common.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 从前慢
 * @Service 生成类实例交个ioc容器管理
 * @Resource 生成的类实例交给SpringIOC容器管理
 * @Override 方法重写标识
 * @Transactional 事务注解
 */
@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;
    @Override
    public CategoryVo findCategoryById(Long categoryId) {
        Category category = categoryMapper.selectById(categoryId);
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        /**
         * String.valueOf有效防止空指针异常
         */
        categoryVo.setId(String.valueOf(category.getId()));
        return categoryVo;
    }

    @Override
    public Result findAll() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Category::getId,Category::getCategoryName);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        /**
         * 和页面交互的对象
         */
        return Result.success(copyList(categories));
    }

    @Override
    public Result findAllDetail() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        /**
         * 和页面交互的对象
         */
        return Result.success(copyList(categories));

    }

    @Override
    public Result categoryDetailById(Long id){
        Category category = categoryMapper.selectById(id);
        return Result.success(copy(category));
    }


    private CategoryVo copy(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        categoryVo.setId(String.valueOf(category.getId()));
        return categoryVo;
    }

    private List<CategoryVo> copyList(List<Category> categoryList) {
        List<CategoryVo> categoryVoList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryVoList.add(copy(category));
        }
        return categoryVoList;
    }
}
