package com.yxj.blog.comment.config;

import com.yxj.blog.comment.handler.CustomInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author 从前慢
 * @Configuration 配置类，注入ioc容器中
 * @Resource 生成的类实例交给SpringIOC容器管理
 * @Override 方法重写标识
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private CustomInterceptor customInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor).addPathPatterns("/comments/create/change");
    }
}
