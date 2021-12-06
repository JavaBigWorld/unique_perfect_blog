package com.yxj.blog.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yxj.blog.common.Error.ErrorCode;
import com.yxj.blog.common.entity.SysUser;
import com.yxj.blog.common.vo.LoginUserVo;
import com.yxj.blog.common.vo.Result;
import com.yxj.blog.common.vo.UserVo;
import com.yxj.blog.user.feign.AuthClient;
import com.yxj.blog.user.mapper.SysUserMapper;
import com.yxj.blog.user.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 从前慢
 * @Transactional 事务注解
 * @Service 生成类实例交个ioc容器管理
 * @Resource 生成的类实例交给SpringIOC容器管理
 * @Override 方法重写标识
 */

@Transactional
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    SysUserMapper sysUserMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private AuthClient authClient;
    @Override
    public SysUser findUserById(Long authorId) {
        SysUser sysUser = sysUserMapper.selectById(authorId);
        if (null == sysUser) {
            sysUser = new SysUser();
            sysUser.setNickname("从前慢");
        }
        return sysUser;
    }

    @Override
    public SysUser findUser(String account, String password) {
        LambdaQueryWrapper<SysUser>  queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.eq(SysUser::getPassword,password);
        queryWrapper.select(SysUser::getAccount,SysUser::getId,SysUser::getAvatar,SysUser::getNickname);
        /**
         * 加快查询速度，查询一条就停止查询
         */
        queryWrapper.last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public Result findUserByToken(String token) {
        /**
         * 1 token合法性校验
         *  是否为空，解析是否成功，redis是否存在
         * 2 如果校验失败，返回错误
         * 3 如果成功，返回对应的结果 LoginUserVo
         * 远程调用auth服务
         */
        SysUser sysUser = authClient.checkToken(token);
        if (sysUser == null) {
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(),ErrorCode.TOKEN_ERROR.getMsg());
        }
        LoginUserVo loginUserVo = new LoginUserVo();
        /**
         * String.valueOf(sysUser.getId()) 防止空指针异常
         */
        loginUserVo.setId(String.valueOf(sysUser.getId()));
        loginUserVo.setNickname(sysUser.getNickname());
        loginUserVo.setAvatar(sysUser.getAvatar());
        loginUserVo.setAccount(sysUser.getAccount());
        return Result.success(loginUserVo);

    }

    @Override
    public UserVo findUserVoById(Long authorId) {

        SysUser sysUser = sysUserMapper.selectById(authorId);
        if (sysUser == null) {
            sysUser = new SysUser();
            sysUser.setId(1L);
            sysUser.setAvatar("/static/img/logo.b3a48c0.png");
            sysUser.setNickname("从前慢");

        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(sysUser,userVo);
        userVo.setId(String.valueOf(sysUser.getId()));
        return userVo;
    }
}
