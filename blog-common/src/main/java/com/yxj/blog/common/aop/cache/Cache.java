package com.yxj.blog.common.aop.cache;

import java.lang.annotation.*;

/**
 * 缓存优化，经常访问的东西放进缓存中
 * @author 从前慢
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {

    long expire() default 1 * 60 * 1000;

    /**
     * 缓存标识 key
     * @return
     */
    String name() default "";
}
