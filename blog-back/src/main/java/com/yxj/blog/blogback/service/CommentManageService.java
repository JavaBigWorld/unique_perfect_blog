package com.yxj.blog.blogback.service;

import com.yxj.blog.blogback.entity.Result;
import com.yxj.blog.blogback.vo.PageVo;

public interface CommentManageService {
    Result removeCommentById(Long commentId);

    Result commentManagement(PageVo pageVo);

    Result selectCommentByContent(String content);
}
