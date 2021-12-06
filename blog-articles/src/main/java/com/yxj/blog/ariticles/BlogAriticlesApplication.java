package com.yxj.blog.ariticles;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 从前慢
 * @SpringBootApplication 启动springboot应用
 * @MapperScan 开启生成dao代理对象功能
 * @EnableDiscoveryClient  开启服务的注册与发现
 * @EnableFeignClients 开启远程调用
 * @EnableSwagger2 开启swagger应用
 *  @EnableAsync 开启多线程
 * @Slf4j 生成日志
 */
@SpringBootApplication(scanBasePackages={"com.yxj.blog"})
@MapperScan("com.yxj.blog.ariticles.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
@EnableAsync
@Slf4j
public class BlogAriticlesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAriticlesApplication.class, args);
        log.info("http：//localhost:7300");
    }

}
