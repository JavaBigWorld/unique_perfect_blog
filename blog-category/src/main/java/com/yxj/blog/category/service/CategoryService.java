package com.yxj.blog.category.service;

import com.yxj.blog.common.vo.CategoryVo;
import com.yxj.blog.common.vo.Result;

/**
 * @author 从前慢
 */
public interface CategoryService {
    /**
     * 根据类别id查询类别信息
     * @param categoryId
     * @return
     */
    CategoryVo findCategoryById(Long categoryId);

    /**
     * 查询所有分类
     * @return
     */
    Result findAll();


    /**
     * 查询所有分类详细内容
     * @return
     */
    Result findAllDetail();

    /**
     * 根据分类id查询分类详细信息
     * @param id
     * @return
     */
    Result categoryDetailById(Long id);
}
