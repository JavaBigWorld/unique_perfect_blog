package com.yxj.blog.common.aop.log;

import java.lang.annotation.*;

/**
 * @author 从前慢
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String module() default "";

    String operator() default "";


}
