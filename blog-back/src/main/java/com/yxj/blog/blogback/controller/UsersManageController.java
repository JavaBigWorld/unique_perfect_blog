package com.yxj.blog.blogback.controller;


import com.yxj.blog.blogback.entity.Result;
import com.yxj.blog.blogback.vo.UsersManageVo;
import com.yxj.blog.blogback.service.UsersManageService;
import com.yxj.blog.blogback.vo.LoginParam;
import com.yxj.blog.blogback.vo.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "用户管理接口", tags = "用户管理" )
@RestController
public class UsersManageController {
    @Resource
    private UsersManageService usersManageService;

    /**
     * 用户管理
     * @return
     */
    @ApiOperation(value = "用户管理的接口",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;用户管理的接口")
    @PostMapping("/userManagement")
    public Result userManagement(@RequestBody PageVo pageVo) {
        return usersManageService.userManagement(pageVo);
    }


    /**
     * 根据名字查询用户
     * @param name
     * @return
     */
    @ApiOperation(value = "根据名字查询用户的接口",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据名字查询用户的接口")
    @GetMapping("/selectUserByName")
    public Result selectUserByName(String name) {

        return usersManageService.selectUserByName(name);
    }

    /**
     * 改变用户状态
     * @return
     */
    @ApiOperation(value = "改变用户状态的接口",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;改变用户状态的接口")
    @PostMapping("/changeUserStatus")
    public Result changeUserStatus(@RequestBody UsersManageVo status) {
        System.out.println("-----------------------------------------------");
        Result result = usersManageService.changeUserStatus(status);
        System.out.println(result);
        System.out.println("-----------------------------------------");
         return result;
    }

    @ApiOperation(value = "登录的接口",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;登录的接口")
    @PostMapping("/login")
    public Result login(@RequestBody LoginParam loginParam) {
        return usersManageService.login(loginParam);
    }


}
