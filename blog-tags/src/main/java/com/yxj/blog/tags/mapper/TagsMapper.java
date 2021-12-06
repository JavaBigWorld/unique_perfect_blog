package com.yxj.blog.tags.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxj.blog.common.entity.Tag;

import java.util.List;

/**
 * @author 从前慢
 */
public interface TagsMapper extends BaseMapper<Tag> {

    /**
     * 根据文章id查询标签
     * @param articleId
     * @return
     */
    List<Tag> findTagsByArticleId(Long articleId);

    /**
     * 查询最热的几个标签
     * @param limit
     * @return
     */
    List<Long> findHotsTagIds(Integer limit);

    /**
     * 根据标签id查询标签信息
     * @param tagIds
     * @return
     */
    List<Tag> findTagsByTagIds(List<Long> tagIds);
}
