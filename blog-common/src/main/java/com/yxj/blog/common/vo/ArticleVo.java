package com.yxj.blog.common.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 从前慢
 * ArticleVo 文章页面对象
 * @Data 生成getter、setter方法
 */
@Data
public class ArticleVo {


    /**
     * 文章id
     */
    private String id;


    /**
     * 文章标题
     */
    private String title;


    /**
     * 文章简介
     */
    private String summary;


    /**
     * 文章评论数量
     */
    private Integer commentCounts;


    /**
     * 文章浏览量
     */
    private Integer viewCounts;



    /**
     * 文章是否置顶
     */
    private Integer weight;


    /**
     * 文章创建时间
     */
    private String createDate;


    /**
     * 作者信息
     */
    private String author;


    /**
     * 作者id
     */
    private Long authorId;

    /**
     * 文章内容
     */
    private ArticleBodyVo body;

    /**
     * 文章标签信息
     */
    private List<TagVo> tags;

    /**
     * 文章类别
     */
    private CategoryVo category;


    /**
     * 作者头像
     */
    private String avatar;

}
