package com.yxj.blog.blogback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 从前慢
 * @Data 生辰getter、setter方法
 * @AllArgsConstructor 生成全参构造函数
 * 统一封装结果集
 */
@Data
@AllArgsConstructor
public class Result {

    /**
     * 成功、失败
     */
    private Boolean success;

    /**
     * 消息码
     */
    private Integer code;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 数据内容
     */
    private Object data;

    /**
     * 数据数目
     * @param data
     * @return
     */
    private Integer num;

    public static Result success(Object data,Integer num){
        return new Result(Boolean.TRUE,200,"success",data,num);
    }

    public static Result fail(Integer code, String msg){
        return new Result(Boolean.FALSE,code,msg,null,0);
    }
}
