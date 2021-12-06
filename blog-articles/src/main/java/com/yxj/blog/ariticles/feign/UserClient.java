package com.yxj.blog.ariticles.feign;

import com.yxj.blog.common.entity.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 从前慢
 * 创建一个客户端接口，该接口调用到blog-tags服务
 * @RequestParam 这个注解不能省略，否则调用失败
 */
@FeignClient("blog-user")
public interface UserClient {
    /**
     * 根据作者id查询作者详细信息
     * @param authorId
     * @return
     */
    @GetMapping("/user/id")
    SysUser findUserById(@RequestParam("authorId") Long authorId);
}
