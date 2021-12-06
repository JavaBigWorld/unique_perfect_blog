package com.yxj.blog.comment.service;

import com.yxj.blog.common.vo.Result;
import com.yxj.blog.common.vo.params.CommentParam;

/**
 * @author 从前慢
 */
public interface CommentsService {
    /**
     * 根据文章id查询所有的评论列表
     * @param id
     * @return
     */
    Result commentsByArticleId(Long id);

    /**
     * 发表评论
     * @param commentParam
     * @return
     */
    Result comment(CommentParam commentParam);
}
