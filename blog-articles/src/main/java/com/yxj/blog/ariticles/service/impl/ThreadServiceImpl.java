package com.yxj.blog.ariticles.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.yxj.blog.ariticles.mapper.ArticlesMapper;
import com.yxj.blog.ariticles.service.ThreadService;
import com.yxj.blog.common.entity.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author 从前慢
 * @Service 生成类实例交个ioc容器管理
 * @Override 方法重写标识
 */
@Service
public class ThreadServiceImpl implements ThreadService {

    /**
     * 此操作在线程池执行 不会影响原有的主线程
     * @param articlesMapper
     * @param article
     */
    @Async("taskExecutor")
    @Override
    public void updateArticleViewCount(ArticlesMapper articlesMapper, Article article) {
        Integer viewCounts = article.getViewCounts();
        Article articleUpdate = new Article();
        articleUpdate.setViewCounts(viewCounts + 1);
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId,article.getId());
        /**
         * 设置一个 为了在多线程的环境下 线程安全
         */
        updateWrapper.eq(Article::getViewCounts,viewCounts);
        /**
         * update article set view_count=100 where view_count=99 and id=11
         *
         */

        articlesMapper.update(articleUpdate,updateWrapper);
    }
}
