package com.yxj.blog.auth.service;

import com.yxj.blog.common.vo.Result;


/**
 * @author 从前慢
 */
public interface LogoutService {

    /**
     * 退出登录
     * @param token
     * @return
     */
    Result logout(String token);
}
