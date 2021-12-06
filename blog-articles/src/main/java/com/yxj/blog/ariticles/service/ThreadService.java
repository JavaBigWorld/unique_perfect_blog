package com.yxj.blog.ariticles.service;

import com.yxj.blog.ariticles.mapper.ArticlesMapper;
import com.yxj.blog.common.entity.Article;

/**
 * @author 从前慢
 */
public interface ThreadService {
    /**
     * 更新阅读数
     * @param articlesMapper
     * @param article
     */
    void updateArticleViewCount(ArticlesMapper articlesMapper, Article article);
}
