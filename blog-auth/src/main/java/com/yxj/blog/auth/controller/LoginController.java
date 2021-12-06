package com.yxj.blog.auth.controller;

import com.yxj.blog.auth.service.LoginService;
import com.yxj.blog.common.aop.log.LogAnnotation;
import com.yxj.blog.common.entity.SysUser;
import com.yxj.blog.common.vo.Result;
import com.yxj.blog.common.vo.params.LoginParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 从前慢
 * @RestController 返回json数据
 * @Api swagger类上接口说明
 * @ApiOperation swagger方法上接口说明
 * @Resource 生成的类实例交给SpringIOC容器管理
 * @PostMapping Post请求
 */
@RestController
@Api(value = "登录接口", tags = "登录管理" )
public class LoginController {


    @Resource
    private LoginService loginService;

    /**
     * 用户登录
     * @param loginParam
     * @return
     */
    @ApiOperation(value = "登录",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;登录的接口")
    @PostMapping("/login")
    @LogAnnotation(module="认证",operator="登录")
    public Result login(@RequestBody LoginParam loginParam) {
        return  loginService.login(loginParam);
    }


    /**
     * 检查token
     * @param token
     * @return
     */
    @ApiOperation(value = "检查token",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;检查token的接口")
    @PostMapping("/checkToken")
    SysUser checkToken(String token){
        return  loginService.checkToken(token);
    }

}
