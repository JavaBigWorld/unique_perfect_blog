package com.yxj.blog.blogback.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxj.blog.blogback.entity.Result;
import com.yxj.blog.blogback.mapper.CategoryMapper;
import com.yxj.blog.blogback.service.CategoryManagementService;
import com.yxj.blog.blogback.vo.CategoryManagementVo;
import com.yxj.blog.blogback.vo.PageVo;
import com.yxj.blog.blogback.entity.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Service
public class CategoryManagementServiceImpl implements CategoryManagementService {

    @Resource
    private CategoryMapper categoryMapper;
    private Integer num;
    @Override
    public Result removeCategoryById(Long categoryId) {
        categoryMapper.deleteById(categoryId);
        List<Category> categories = categoryMapper.selectList(null);
        num = categories.size();
        return Result.success(null,num);
    }

    @Override
    public Result selectCategoryByName(String name) {
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.like("category_name",name);
        List<Category> categories = categoryMapper.selectList(categoryQueryWrapper);
        List<CategoryManagementVo> categoryManagementVos = new ArrayList<>();
        for (Category category : categories) {
            CategoryManagementVo categoryManagementVo = new CategoryManagementVo();
            BeanUtils.copyProperties(category,categoryManagementVo);
            categoryManagementVos.add(categoryManagementVo);
        }
        num = categories.size();
        return Result.success(categoryManagementVos,num);
    }

    @Override
    public Result categoryManagement(PageVo pageVo) {
        Integer start = (pageVo.getPageNum() - 1) * pageVo.getPageSize();
        Integer offset = pageVo.getPageSize();
        List<Category> categories = categoryMapper.selectData(start,offset);
        List<CategoryManagementVo> categoryManagementVos = new ArrayList<>();
        for (Category category : categories) {
            CategoryManagementVo categoryManagementVo = new CategoryManagementVo();
            BeanUtils.copyProperties(category,categoryManagementVo);
            categoryManagementVos.add(categoryManagementVo);
        }
        List<Category> categories2 = categoryMapper.selectList(null);
        num = categories2.size();
        return Result.success(categoryManagementVos,num);
    }


}
