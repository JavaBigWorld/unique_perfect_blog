package com.yxj.blog.blogback.service;

import com.yxj.blog.blogback.entity.Result;
import com.yxj.blog.blogback.vo.LoginParam;
import com.yxj.blog.blogback.vo.PageVo;
import com.yxj.blog.blogback.vo.UsersManageVo;

public interface UsersManageService {

    Result userManagement(PageVo pageVo);

    Result selectUserByName(String name);

    Result  changeUserStatus(UsersManageVo status);


    /**
     * 用户登录
     * @param loginParam
     * @return
     */
    Result login(LoginParam loginParam);
}
