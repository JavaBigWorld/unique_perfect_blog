package com.yxj.blog.common.entity;

import lombok.Data;

/**
 * @author 从前慢
 */
@Data
public class ArticleBody {

    private Long id;
    private String content;
    private String contentHtml;
    private Long articleId;
}
