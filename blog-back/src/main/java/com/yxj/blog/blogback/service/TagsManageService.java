package com.yxj.blog.blogback.service;

import com.yxj.blog.blogback.entity.Result;
import com.yxj.blog.blogback.vo.PageVo;

public interface TagsManageService {
    Result removeTagById(Long tagId);

    Result tagsManagement(PageVo pageVo);

    Result selectTagByName(String name);
}
