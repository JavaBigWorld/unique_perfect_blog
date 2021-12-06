package com.yxj.blog.common.vo;

import lombok.Data;

/**
 * @author 从前慢
 * TagVo 标签页面对象
 * @Data 生成getter、setter方法
 */
@Data
public class TagVo {

    /**
     * 标签id
     */
    private String id;

    /**
     * 标签名字
     */
    private String tagName;

    /**
     * 标签图片
     */
    private String avatar;
}
