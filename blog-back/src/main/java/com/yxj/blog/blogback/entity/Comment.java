package com.yxj.blog.blogback.entity;

import lombok.Data;

/**
 * @author 从前慢
 * @Data 生成getter、setter方法
 */
@Data
public class Comment {

    /**
     * 评论id
     */
    private Long id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private Long createDate;

    /**
     * 评论文章id
     */
    private Long articleId;

    /**
     * 文章作者id
     */
    private Long authorId;

    /**
     * 父评论id
     */
    private Long parentId;

    /**
     * 被评论作者的id
     */
    private Long toUid;

    /**
     * 评论的层数
     */
    private Integer level;
}
