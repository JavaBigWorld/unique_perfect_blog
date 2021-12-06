package com.yxj.blog.blogback.vo;

import lombok.Data;

@Data
public class UsersManageVo {

    /**
     * 用户id
     *
     */
    private String id;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户是否具有管理员权限
     */
    private Boolean status;
}
