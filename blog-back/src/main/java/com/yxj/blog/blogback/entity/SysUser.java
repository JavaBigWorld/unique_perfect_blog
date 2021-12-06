package com.yxj.blog.blogback.entity;

import lombok.Data;

/**
 * @author 从前慢
 */
@Data
public class SysUser{

    /**
     * 用户id
     * @TableId(type = IdType.ASSIGN_ID)  默认id类型
     * 以后 用户多了之后，要进行分表操作，id就需要用分布式id了
     * @TableId(type = IdType.AUTO) 数据库自增
     *
     */
    private Long id;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 是否管理员
     */
    private Integer admin;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户注册时间
     */
    private Long createDate;

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户最后登录时间
     */
    private Long lastLogin;

    /**
     * 用户手机号
     */
    private String mobilePhoneNumber;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户加密盐
     */
    private String salt;

    /**
     * 用户状态
     */
    private Boolean status;
}
