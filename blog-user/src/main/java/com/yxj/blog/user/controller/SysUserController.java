
package com.yxj.blog.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yxj.blog.common.entity.SysUser;
import com.yxj.blog.common.vo.Result;
import com.yxj.blog.common.vo.UserVo;
import com.yxj.blog.user.mapper.SysUserMapper;
import com.yxj.blog.user.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 从前慢
 * @RestController restapi注解
 * @Api swagger类上接口说明
 * @Resource 依赖注入
 * @GetMapping Get请求
 * @PostMapping Post请求
 */

@RestController
@Api(value = "用户接口", tags = "用户管理" )
public class SysUserController {

    @Resource
    SysUserMapper sysUserMapper;

    @Resource
    SysUserService sysUserService;


    @GetMapping("/user/id")
    @ApiOperation(value = "根据作者id查询作者详细信息",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据作者id查询作者详细信息的接口")
    SysUser findUserById(@RequestParam("authorId") Long authorId) {
        return sysUserService.findUserById(authorId);
    }


    @PostMapping("/user/findUser")
    @ApiOperation(value = "根据用户名和密码查找用户",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据用户名和密码查找用户的接口")
    public SysUser findUser(String account, String password) {
        SysUser sysUser = sysUserService.findUser(account, password);
        return sysUser;
    }

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    @ApiOperation(value = "根据token获取用户信息",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据token获取用户信息的接口")
    @GetMapping("/user/currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token) {
        return sysUserService.findUserByToken(token);
    }


    /**
     * 根据账号查找用户
     * @param account
     * @return
     */
    @ApiOperation(value = "根据账号查找用户",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据账号查找用户的接口")
    @GetMapping("/user/findUserByAccount")
    SysUser findUserByAccount(String account) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(SysUser::getAccount);
        queryWrapper.eq(SysUser::getAccount, account);
        /**
         * 加快查询速度
         */
        queryWrapper.last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }


    /**
     * 注册用户
     * @param sysUser
     */
    @PostMapping("/user/save")
    @ApiOperation(value = "注册用户",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;注册用户的接口")
    public void save(@RequestBody SysUser sysUser) {
        /**
         * 保存用户 id会自动生成
         * 这个地方默认生成的id是分布式id 雪花算法
         */
        sysUserMapper.insert(sysUser);
    }

    /**
     * 根据作者id查询作者信息
     * @param authorId
     * @return
     */
    @GetMapping("/findUserVoById/id")
    @ApiOperation(value = "根据作者id查询作者信息",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据作者id查询作者信息的接口")
    UserVo findUserVoById(Long authorId){

        return  sysUserService.findUserVoById(authorId);

    }
}
