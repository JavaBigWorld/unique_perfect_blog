package com.yxj.blog.blogback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxj.blog.blogback.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 从前慢
 * 文章
 */
public interface ArticlesMapper  extends BaseMapper<Article> {

    @Select("select * from article limit #{start},#{offset}")
    List<Article> selectData(@Param("start") Integer start, @Param("offset") Integer offset);
}
