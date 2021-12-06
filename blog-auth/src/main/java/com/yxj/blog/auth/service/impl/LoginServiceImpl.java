package com.yxj.blog.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.yxj.blog.auth.feign.UserClient;
import com.yxj.blog.auth.service.LoginService;
import com.yxj.blog.auth.utils.JwtUtils;
import com.yxj.blog.common.Error.ErrorCode;
import com.yxj.blog.common.entity.SysUser;
import com.yxj.blog.common.vo.Result;
import com.yxj.blog.common.vo.params.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 从前慢
 * @Transactional 事务注解
 * @Service 生成类实例交个ioc容器管理
 * @Resource 生成的类实例交给SpringIOC容器管理
 * @Override 方法重写标识
 *
 */

@Transactional
@Service
public class LoginServiceImpl implements LoginService {
    private static final String salt = "congqianman";

    @Resource
    private UserClient userClient;
    @Resource
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public Result login(LoginParam loginParam) {
        /**
         * 1 检查参数是否合法
         * 2 根据用户名和密码去user表中查询
         * 3 如果不存在 登录失败
         * 4 如果存在，使用jwt 生成token 返回给前端
         * 5 token 放入redis当中，redis token：user信息 设置过期时间(登录认证的时候，先认证token字符串
         * 是否合法，去redis认证是否存在)
         */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        password = DigestUtils.md5Hex(password+salt);
        /**
         * 远程调用用户服务，获取用户信息
         */
        SysUser sysUser = userClient.findUser(account,password);
        if (sysUser == null) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        String token = JwtUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);

        return Result.success(token);
    }

    @Override
    public SysUser checkToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Map<String, Object> map = JwtUtils.checkToken(token);
        if (map == null) {
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)){
            return null;
        }
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }
}
