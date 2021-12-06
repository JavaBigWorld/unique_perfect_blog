package com.yxj.blog.blogback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxj.blog.blogback.entity.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 从前慢
 */
public interface CategoryMapper extends BaseMapper<Category> {

    @Select("select * from category limit #{start},#{offset}")
    List<Category> selectData(@Param("start") Integer start, @Param("offset") Integer offset);
}
