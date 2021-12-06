package com.yxj.blog.auth.feign;

import com.yxj.blog.common.entity.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 从前慢
 */
@FeignClient("blog-user")
public interface UserClient {

    /**
     * 根据用户名和密码查找用户
     * @param account
     * @param password
     * @return
     */

    @PostMapping("/user/findUser")
    public SysUser findUser(@RequestParam("account") String account, @RequestParam("password") String password);


    /**
     * 根据账号查找用户
     * @param account
     * @return
     */
    @GetMapping("/user/findUserByAccount")
    SysUser findUserByAccount(@RequestParam("account")String account);

    /**
     * 注册用户
     * @param sysUser
     * @return
     */
    @PostMapping("/user/save")
    void save(@RequestBody SysUser sysUser);
}
