package com.yxj.blog.user.service;

import com.yxj.blog.common.entity.SysUser;
import com.yxj.blog.common.vo.Result;
import com.yxj.blog.common.vo.UserVo;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 从前慢
 */
public interface SysUserService {

    /**
     * 根据作者id查询作者详细信息
     * @param authorId
     *
     * @return
     */
    SysUser findUserById(@RequestParam("authorId") Long authorId);


    /**
     * 根据用户名和密码查找用户
     * @param account
     * @param password
     * @return
     */
    SysUser findUser(String account, String password);

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    Result findUserByToken(String token);

    /**
     * 根据作者id查询作者信息
     * @param authorId
     * @return
     */
    UserVo findUserVoById(Long authorId);
}
