package com.yxj.blog.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 从前慢
 * @SpringBootApplication 启动springboot应用
 * @EnableDiscoveryClient  开启服务的注册与发现
 * @EnableFeignClients 开启远程调用
 * @Slf4j 生成日志
 */
@SpringBootApplication(scanBasePackages={"com.yxj.blog"})
@EnableDiscoveryClient
@EnableFeignClients
//@EnableSwagger2Doc
@Slf4j
public class BlogGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogGatewayApplication.class, args);
        log.info("http://localhost:7200");
    }

}
