package com.yxj.blog.tags.service;

import com.yxj.blog.common.vo.Result;
import com.yxj.blog.common.vo.TagVo;

import java.util.List;

/**
 * @author 从前慢
 */
public interface TagsService {

    /**
     * 通过文章id查询文章所对应的标签信息
     * @param articleId
     * @return
     */
    List<TagVo> findTagsByArticleId(Long articleId);

    /**
     * 查询最热标签
     * @param limit
     * @return
     */
    Result hot(Integer limit);

    /**
     * 查询所有的文章标签
     * @return
     */
    Result findAll();


    /**
     * 查询所有的文章标签详细内容
     * @return
     */
    Result findAllDetail();

    /**
     * 根据标签id查询标签详细信息
     * @param id
     * @return
     */
    Result findAllDetailById(Long id);
}
