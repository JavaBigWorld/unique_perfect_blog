package com.yxj.blog.user.feign;

import com.yxj.blog.common.entity.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 从前慢
 * 创建一个客户端接口，该接口调用到blog-tags服务
 * @RequestParam 这个注解不能省略，否则调用失败
 */
@FeignClient("blog-auth")
public interface AuthClient {
    /**
     * 检查token是否合法
     * @param token
     * @return
     */
    @PostMapping("/checkToken")
    SysUser checkToken(@RequestParam("token")String token);
}
