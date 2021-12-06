package com.yxj.blog.common.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 从前慢
 * @Data 生成getter、setter方法
 */
@Data
public class CommentVo  {

    /**
     * 评论id
     */
    private String id;


    /**
     * 评论内容
     */
    private String content;


    /**
     * 评论时间
     */
    private String createDate;


    /**
     * 文章作者
     */
    private UserVo author;


    /**
     * 子评论
     */
    private List<CommentVo> childrens;


    /**
     * 被评论作者的id
     */
    private UserVo toUser;


    /**
     * 评论的层数
     */
    private Integer level;


}
