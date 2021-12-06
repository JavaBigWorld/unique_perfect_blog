package com.yxj.blog.auth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yxj.blog.auth.feign.UserClient;
import com.yxj.blog.auth.service.RegisterService;
import com.yxj.blog.auth.utils.JwtUtils;
import com.yxj.blog.common.Error.ErrorCode;
import com.yxj.blog.common.entity.SysUser;
import com.yxj.blog.common.vo.Result;
import com.yxj.blog.common.vo.params.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 从前慢
 * @Transactional 事务注解
 * @Service 生成类实例交个ioc容器管理
 * @Resource 生成的类实例交给SpringIOC容器管理
 * @Override 方法重写标识
 */
@Transactional
@Service
public class RegisterServiceImpl implements RegisterService {

    private static final String salt = "congqianman";
    private String[] avatar = new String[]{"/avatar/admin.png","/avatar/lisi.png","/avatar/xiaogang.png","/avatar/xiaohong.png" +
            "/avatar/xiaoling.png","/avatar/xiaomei.png","/avatar/xiaowan.png","/avatar/xiaoxin.png","/avatar/xiaoyin.png","/avatar/zhangsan.png"};
    @Resource
    private UserClient userClient;
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public Result register(LoginParam loginParam) {
        /**
         * 1 判断参数 是否合法
         * 2 判断账户是否存在，存在 返回账户已经被注册
         * 3 不存在，注册账户
         * 4 生成token
         * 5 存入redis并返回
         * 6 注意加上事务，一旦中间的任何过程出现问题，注册的用户 需要回滚
         */
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        String nickname = loginParam.getNickname();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password) || StringUtils.isBlank(nickname)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }

        /**
         * 远程调用user服务
         */
        SysUser sysUser = userClient.findUserByAccount(account);
        if (sysUser != null) {
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(),ErrorCode.ACCOUNT_EXIST.getMsg());
        }
        sysUser = new SysUser();
        sysUser.setNickname(nickname);
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtils.md5Hex(password + salt));
        sysUser.setCreateDate(System.currentTimeMillis());
        sysUser.setLastLogin(System.currentTimeMillis());

        sysUser.setAvatar(avatar[(int) (Math.random() * avatar.length)]);
        /**
         * 1 为true
         */
        sysUser.setAdmin(1);
        sysUser.setDeleted(0);
        /**
         *  0 为false
         */
        sysUser.setSalt("");
        sysUser.setStatus(Boolean.FALSE);
        sysUser.setEmail("");
        userClient.save(sysUser);
        String token = JwtUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_"+token, JSONObject.toJSONString(sysUser),1, TimeUnit.DAYS);
        return Result.success(token);
    }
}
