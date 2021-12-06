package com.yxj.blog.blogback.entity;

import lombok.Data;

/**
 * @author 从前慢
 */
@Data
public class PageParam {

    private Integer currentPage;

    private Integer pageSize;

    private String queryString;
}
