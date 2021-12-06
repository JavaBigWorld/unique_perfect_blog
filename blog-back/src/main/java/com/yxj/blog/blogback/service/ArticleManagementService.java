package com.yxj.blog.blogback.service;

import com.yxj.blog.blogback.entity.Result;
import com.yxj.blog.blogback.vo.PageVo;

public interface ArticleManagementService {
    Result articleManagement(PageVo pageVo);


    Result selectArticleByName(String name);

    Result removeArticleById(Long articleId);
}
