package com.yxj.blog.blogback.vo;

import lombok.Data;

@Data
public class PageVo {
    private Integer pageNum;
    private Integer pageSize;
    private Integer total;
}
