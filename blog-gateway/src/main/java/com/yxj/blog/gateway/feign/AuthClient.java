package com.yxj.blog.gateway.feign;

import com.yxj.blog.common.entity.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 从前慢
 */
@FeignClient("blog-auth")
public interface AuthClient {

    /**
     * 检查token
     * @param token
     * @return
     */
    @PostMapping("/checkToken")
    SysUser checkToken(@RequestParam("token") String token);

}
