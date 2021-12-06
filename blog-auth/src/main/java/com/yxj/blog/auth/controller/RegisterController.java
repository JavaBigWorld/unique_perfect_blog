package com.yxj.blog.auth.controller;

import com.yxj.blog.auth.service.RegisterService;
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
 * @Resource 生成的类实例交给SpringIOC容器管理
 * @PostMapping Post请求
 * @Api swagger类上接口说明
 * @ApiOperation swagger方法上接口说明
 */

@RestController
@Api(value = "注册接口", tags = "注册管理" )
public class RegisterController {

    @Resource
    private RegisterService registerService;

    @ApiOperation(value = "注册",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;注册的接口")
    @PostMapping("/register")
    public Result register(@RequestBody LoginParam loginParam){
        return registerService.register(loginParam);
    }
}
