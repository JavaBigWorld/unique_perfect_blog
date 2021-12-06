package com.yxj.blog.common.vo.params;

import lombok.Data;

/**
 * 评论
 */
@Data
public class CommentParam {

    private Long articleId;

    private String content;

    private Long parent;

    private Long toUserId;
}
