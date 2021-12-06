package com.yxj.blog.common.dos;

import lombok.Data;

/**
 * 文章归档
 * @author 从前慢
 */
@Data
public class Archives {

    private Integer year;

    private Integer month;

    private Long count;
}
