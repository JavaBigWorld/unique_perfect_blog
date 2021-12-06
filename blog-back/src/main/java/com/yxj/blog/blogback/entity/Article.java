package com.yxj.blog.blogback.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 从前慢
 * Article 文章
 * @Data 生成getter、setter方法
 */
@TableName(value = "article")
@Data
public class Article {

    public static final Integer Article_TOP = 1;

    public static final Integer Article_Common = 0;

    /**
     * 文章id
     */
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章简介
     */
    private String summary;

    /**
     * 文章评论数量
     */
    private Integer commentCounts;

    /**
     * 文章浏览量
     */
    private Integer viewCounts;

    /**
     * 文章是否置顶
     */
    private Integer weight;


    /**
     * 文章创建时间
     */
    private Long createDate;


    /**
     * 文章作者id
     */
    private Long authorId;
    /**
     * 文章内容id
     */
    private Long bodyId;
    /**
     * 文章类别id
     */
    private Long categoryId;


}
