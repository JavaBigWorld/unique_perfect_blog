package com.yxj.blog.ariticles.feign;

import com.yxj.blog.common.vo.TagVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 从前慢
 * 创建一个客户端接口，该接口调用到blog-tags服务
 * @RequestParam 这个注解不能省略，否则调用失败
 */
@FeignClient("blog-tags")
public interface TagsClient {
    @GetMapping("/tags/findTags")
    List<TagVo> findTagsByArticleId(@RequestParam("articleId") Long articleId);
}
