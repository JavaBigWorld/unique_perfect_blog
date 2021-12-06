package com.yxj.blog.ariticles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author 从前慢
 * @Configuration 配置类，注入ioc容器中
 * @Bean 逐日ioc容器中
 * 整合线程池的使用，异步调用，提高效率
 */
@Configuration
public class ThreadPoolConfig {
    @Bean("taskExecutor")
    public Executor asyncServiceExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        /**
         * 设置核心线程数
         */
        threadPoolTaskExecutor.setCorePoolSize(5);
        /**
         * 设置最大线程数
         */
        threadPoolTaskExecutor.setMaxPoolSize(20);
        /**
         * 设置队列大小
         */
        threadPoolTaskExecutor.setQueueCapacity(Integer.MAX_VALUE);
        /**
         * 设置线程活跃时间
         */
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        /**
         * 设置默认线程名称
         */
        threadPoolTaskExecutor.setThreadNamePrefix("从前慢博客项目");
        /**
         * 等待所有任务结束后再关闭线程池
         */
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        /**
         * 执行初始化
         */
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;

    }

}
