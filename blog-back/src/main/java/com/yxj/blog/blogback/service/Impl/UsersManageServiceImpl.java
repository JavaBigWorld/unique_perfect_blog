package com.yxj.blog.blogback.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxj.blog.blogback.Error.ErrorCode;
import com.yxj.blog.blogback.entity.Result;
import com.yxj.blog.blogback.mapper.SysUserMapper;
import com.yxj.blog.blogback.service.UsersManageService;
import com.yxj.blog.blogback.util.JwtUtils;
import com.yxj.blog.blogback.vo.LoginParam;
import com.yxj.blog.blogback.vo.PageVo;
import com.yxj.blog.blogback.vo.UsersManageVo;
import com.yxj.blog.blogback.entity.SysUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersManageServiceImpl implements UsersManageService {

    @Resource
    private SysUserMapper sysUserMapper;

    private Integer num;
    private static final String salt = "congqianman";

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public Result userManagement(PageVo pageVo) {
        Integer start = (pageVo.getPageNum() - 1) * pageVo.getPageSize();
        Integer offset = pageVo.getPageSize();
        List<SysUser> sysUsers = sysUserMapper.selectData(start,offset);
        ArrayList<UsersManageVo> usersManageVos = new ArrayList<>();
        for (SysUser sysUser : sysUsers) {
            UsersManageVo usersManageVo = new UsersManageVo();
            BeanUtils.copyProperties(sysUser,usersManageVo);
            usersManageVo.setId(sysUser.getId().toString());
            usersManageVos.add(usersManageVo);
        }
        List<SysUser> sysUsers2 = sysUserMapper.selectList(null);
        num = sysUsers2.size();
        return Result.success(usersManageVos,num);
    }

    @Override
    public Result selectUserByName(String name) {
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        sysUserQueryWrapper.like("account",name);
        List<SysUser> sysUsers = sysUserMapper.selectList(sysUserQueryWrapper);
        ArrayList<UsersManageVo> usersManageVos = new ArrayList<>();
        for (SysUser sysUser : sysUsers) {
            UsersManageVo usersManageVo = new UsersManageVo();
            BeanUtils.copyProperties(sysUser,usersManageVo);
            usersManageVos.add(usersManageVo);
        }
        num = sysUsers.size();
        return Result.success(usersManageVos,num);
    }

    @Override
    public Result changeUserStatus(UsersManageVo status) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(status,sysUser);

        sysUser.setId(Long.valueOf(status.getId()));
        sysUserMapper.updateById(sysUser);
        List<SysUser> sysUsers2 = sysUserMapper.selectList(null);
        num = sysUsers2.size();
        return Result.success(null,num);
    }

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
        String account = loginParam.getUsername();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        password = DigestUtils.md5Hex(password+salt);

        /**
         * 获取用户信息
         */
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        SysUser sysUser = sysUserMapper.selectOne(sysUserQueryWrapper.eq("account", account).eq("password", password));
        if (sysUser == null) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        if(sysUser.getStatus() != true){
            return Result.fail(ErrorCode.NO_PERMISSION.getCode(),ErrorCode.NO_PERMISSION.getMsg());
        }

        String token = JwtUtils.createToken(sysUser.getId());

        return Result.success(token,0);
    }
}
