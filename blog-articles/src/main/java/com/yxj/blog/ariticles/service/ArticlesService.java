package com.yxj.blog.ariticles.service;

import com.yxj.blog.common.vo.Result;
import com.yxj.blog.common.vo.params.ArticleParam;
import com.yxj.blog.common.vo.params.PageParams;

/**
 * @author 从前慢
 */
public interface ArticlesService {

    /**
     * 分页查询 文章列表
     * @param pageParams
     * @return
     */
    Result listArticle(PageParams pageParams);

    /**
     * 查询首页最热文章
     *  @param limit
     * @return
     */
    Result hotArticle(Integer limit);


    /**
     * 查询首页最新文章
     * @param limit
     * @return
     */
    Result newArticle(Integer limit);

    /**
     * 首页文章归档
     * @return
     */
    Result listArchives();

    /**
     * 根据文章id查询文章详情
     * @param articleId
     * @return
     */
    Result findArticleById(Long articleId);

    /**
     * 发布文章
     * @param articleParam
     * @return
     */
    Result publish(ArticleParam articleParam);

    /**
     * 修改文章评论数
     * @param articleId
     * @return
     */
    void updatacommentCounts(Long articleId);
}
