package com.yxj.blog.comment;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 从前慢
 * @SpringBootApplication 启动springboot应用
 * @MapperScan 开启生成dao代理对象功能
 * @EnableDiscoveryClient  开启服务的注册与发现
 * @EnableFeignClients 开启远程调用
 * @Slf4j 生成日志
 */
@SpringBootApplication(scanBasePackages={"com.yxj.blog"})
@MapperScan("com.yxj.blog.comment.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
@Slf4j
public class BlogCommentApplication {

    public static void main(String[] args) {
        log.info("http://localhost:7800");
        SpringApplication.run(BlogCommentApplication.class, args);
    }

}
