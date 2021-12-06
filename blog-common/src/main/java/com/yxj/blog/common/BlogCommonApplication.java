package com.yxj.blog.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author 从前慢
 * @SpringBootApplication 启动springboot应用
 * @Slf4j 生成日志
 */
@SpringBootApplication(scanBasePackages={"com.yxj.blog"})
@Slf4j
public class BlogCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogCommonApplication.class, args);
        log.info("http://localhost:7500");
    }

}
