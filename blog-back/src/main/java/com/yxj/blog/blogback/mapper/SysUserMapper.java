package com.yxj.blog.blogback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxj.blog.blogback.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 从前慢
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("select * from sys_user limit #{start},#{offset}")
    List<SysUser> selectData(@Param("start") Integer start, @Param("offset") Integer offset);
}
