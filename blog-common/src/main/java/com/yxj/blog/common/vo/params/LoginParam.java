package com.yxj.blog.common.vo.params;

import lombok.Data;

/**
 * @author 从前慢
 * @Data 生成getter、setter方法
 */
@Data
public class LoginParam{

    private String account;

    private String password;

    private String nickname;
}
