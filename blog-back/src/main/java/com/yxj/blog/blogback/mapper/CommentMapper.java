package com.yxj.blog.blogback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxj.blog.blogback.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 从前慢
 */
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("select * from comment limit #{start},#{offset}")
    List<Comment> selectData(@Param("start") Integer start, @Param("offset") Integer offset);
}
