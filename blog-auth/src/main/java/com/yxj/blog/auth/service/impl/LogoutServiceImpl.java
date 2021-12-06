package com.yxj.blog.auth.service.impl;

import com.yxj.blog.auth.service.LogoutService;
import com.yxj.blog.common.vo.Result;
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
public class LogoutServiceImpl implements LogoutService {
    @Resource
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_"+ token);
        return Result.success(null);
    }
}
