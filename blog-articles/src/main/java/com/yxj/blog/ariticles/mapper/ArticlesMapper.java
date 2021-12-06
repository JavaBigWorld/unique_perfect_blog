package com.yxj.blog.ariticles.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxj.blog.common.dos.Archives;
import com.yxj.blog.common.entity.Article;

import java.util.List;

/**
 * @author 从前慢
 * 文章
 */
public interface ArticlesMapper  extends BaseMapper<Article> {
    /**
     * 首页文章归档
     * @return
     */
    List<Archives> listArchives();

    /**
     * 根据文章归档查询文章
     * @param page
     * @param categoryId
     * @param tagId
     * @param year
     * @param month
     * @return
     */
    IPage<Article> listArticle(Page<Article> page,Long categoryId,Long tagId,String year,String month);
}
