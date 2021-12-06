package com.yxj.blog.comment.feign;

import com.yxj.blog.common.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 从前慢
 * 创建一个客户端接口，该接口调用到blog-user服务
 * @RequestParam 这个注解不能省略，否则调用失败
 * @FeignClient 客户端
 * @GetMapping Get请求
 */
@FeignClient("blog-user")
public interface UserClient {
    /**
     * 根据作者id查询作者信息
     * @param authorId
     * @return
     */
    @GetMapping("/findUserVoById/id")
    UserVo findUserVoById(@RequestParam("authorId") Long authorId);
}
