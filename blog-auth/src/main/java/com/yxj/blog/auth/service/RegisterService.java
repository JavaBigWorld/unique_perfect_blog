package com.yxj.blog.auth.service;

import com.yxj.blog.common.vo.Result;
import com.yxj.blog.common.vo.params.LoginParam;

/**
 * @author 从前慢
 */
public interface RegisterService {

    /**
     * 注册用户
     * @param loginParam
     * @return
     */
    Result register(LoginParam loginParam);
}
