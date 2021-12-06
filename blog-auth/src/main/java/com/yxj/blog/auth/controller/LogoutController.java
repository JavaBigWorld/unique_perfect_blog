package com.yxj.blog.auth.controller;

import com.yxj.blog.auth.service.LogoutService;
import com.yxj.blog.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 从前慢
 * @RestController 返回json数据
 * @Api swagger类上接口说明
 * @Resource 生成的类实例交给SpringIOC容器管理
 * @GetMapping Get请求
 * @ApiOperation swagger方法上接口说明
 */

@RestController
@Api(value = "退出接口", tags = "退出管理" )
public class LogoutController {


    @Resource
    private LogoutService logoutService;

    @GetMapping("/logout")
    @ApiOperation(value = "退出",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;退出的接口")
    public Result logout(@RequestHeader("Authorization")String token)
    {
        return  logoutService.logout(token);
    }

}
