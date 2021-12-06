package com.yxj.blog.comment.feign;


import com.yxj.blog.common.aop.log.LogAnnotation;
import com.yxj.blog.common.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("blog-articles")
public interface ArticleClient {

    /**
     * 修改文章评论数
     * @param articleId
     * @return
     */
    @PostMapping("/articles/update")
    public void updatacommentCounts(@RequestParam("articleId") Long articleId);
}
