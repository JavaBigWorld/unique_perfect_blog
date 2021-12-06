package com.yxj.blog.blogback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxj.blog.blogback.entity.Tag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 从前慢
 */
public interface TagsMapper extends BaseMapper<Tag> {

    @Select("select * from tag limit #{start},#{offset}")
    List<Tag> selectData(@Param("start") Integer start, @Param("offset") Integer offset);
}
