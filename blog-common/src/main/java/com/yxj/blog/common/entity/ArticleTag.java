package com.yxj.blog.common.entity;

import lombok.Data;

/**
 * @author 从前慢
 */
@Data
public class ArticleTag {

    private Long id;

    private Long articleId;

    private Long tagId;
}
