package com.yxj.blog.blogback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author 从前慢
 */
@Data
public class Admin {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;
}
