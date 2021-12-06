package com.yxj.blog.blogback.service;

import com.yxj.blog.blogback.entity.Result;
import com.yxj.blog.blogback.vo.PageVo;

public interface CategoryManagementService {
    Result removeCategoryById(Long categoryId);

    Result selectCategoryByName(String name);
    Result categoryManagement(PageVo pageVo);
}
