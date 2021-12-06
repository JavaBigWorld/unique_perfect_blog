package com.yxj.blog.blogback.vo;

import lombok.Data;

@Data
public class ArticleManagementVo {

    /**
     * 文章id
     */
    private String articleId;

    /**
     * 文章作者
     */
    private String author;


    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章评论数量
     */
    private Integer commentCounts;

    /**
     * 文章浏览量
     */
    private Integer viewCounts;
}
