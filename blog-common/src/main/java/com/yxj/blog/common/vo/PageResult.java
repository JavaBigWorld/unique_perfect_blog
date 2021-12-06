package com.yxj.blog.common.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 从前慢
 */
@Data
public class PageResult<T> {

    private List<T> list;

    private Long total;
}
