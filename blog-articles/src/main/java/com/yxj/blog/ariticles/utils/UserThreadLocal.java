package com.yxj.blog.ariticles.utils;

import com.yxj.blog.common.entity.SysUser;

/**
 * 保存用户信息
 * @author 从前慢
 */
public class UserThreadLocal {
    private UserThreadLocal() {

    }

    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    public static void put(SysUser sysUser) {
        LOCAL.set(sysUser);
    }

    public static SysUser get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }
}
