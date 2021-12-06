package com.yxj.blog.auth.service;

import com.yxj.blog.common.entity.SysUser;
import com.yxj.blog.common.vo.Result;
import com.yxj.blog.common.vo.params.LoginParam;

/**
 * @author 从前慢
 */
public interface LoginService {

    /**
     * 用户登录
     * @param loginParam
     * @return
     */
    Result login(LoginParam loginParam);

    /**
     * 检查token
     * @param token
     * @return
     */
    SysUser checkToken(String token);
}
