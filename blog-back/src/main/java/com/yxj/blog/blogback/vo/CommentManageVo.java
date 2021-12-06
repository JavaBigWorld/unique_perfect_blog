package com.yxj.blog.blogback.vo;

import lombok.Data;

@Data
public class CommentManageVo {

    /**
     * 评论id
     */
    private String id;

    /**
     * 评论内容
     */
    private String content;


    /**
     * 评论文章
     */
    private String article;

    /**
     * 评论作者
     */
    private String author;
}
