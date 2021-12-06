package com.yxj.blog.common.vo;

import lombok.Data;

/**
 * @author 从前慢
 * @Data 生成getter、setter方法
 *
 */
@Data
public class LoginUserVo {

    /**
     * 用户id
     */
    private String id;


    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;
}
