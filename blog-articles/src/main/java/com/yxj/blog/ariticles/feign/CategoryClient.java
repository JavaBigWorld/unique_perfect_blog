package com.yxj.blog.ariticles.feign;

import com.yxj.blog.common.vo.CategoryVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 从前慢
 * @FeignClient("blog-category") feign客户端
 */
@FeignClient("blog-category")
public interface CategoryClient {

    /**
     * 根据类别id查询类别信息
     * @param categoryId
     * @return
     */
    @GetMapping("/categorys/findCategoryById")
    public CategoryVo findCategoryById(@RequestParam("categoryId") Long categoryId);

}
