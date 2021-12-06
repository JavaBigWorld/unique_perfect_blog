package com.yxj.blog.blogback.vo;

import lombok.Data;

/**
 * @author 从前慢
 * Tag 标签
 * @Data 生成getter、setter方法
 */
@Data
public class TagsManageVo {

    /**
     * 标签id
     */
    private Long id;

    /**
     * 标签名字
     */
    private String tagName;


    /**
     * 标签图片
     */
    private String avatar;


}

