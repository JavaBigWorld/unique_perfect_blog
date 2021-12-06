package com.yxj.blog.common.vo.params;

import lombok.Data;

/**
 * 分页参数
 * @Data 生成getter、setter方法
 */
@Data
public class PageParams {

    private Integer page = 1;

    private Integer pageSize = 10;

    private Long categoryId;

    private Long tagId;

    private String year;

    private String month;

    public String getMonth(){
        if (this.month != null && this.month.length() == 1){
            return "0"+this.month;
        }
        return this.month;
    }
}
