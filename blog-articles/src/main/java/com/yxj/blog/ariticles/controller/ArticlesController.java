package com.yxj.blog.ariticles.controller;

import com.yxj.blog.ariticles.service.ArticlesService;
import com.yxj.blog.common.aop.cache.Cache;
import com.yxj.blog.common.aop.log.LogAnnotation;
import com.yxj.blog.common.vo.Result;
import com.yxj.blog.common.vo.params.ArticleParam;
import com.yxj.blog.common.vo.params.PageParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 从前慢
 * @RestController 返回json数据
 * @Api swagger类上接口说明
 * @Resource 生成的类实例交给SpringIOC容器管理
 * @ApiOperation swagger方法上接口说明
 * @PostMapping post请求
 * @LogAnnotation 记录日志
 * @Cache 缓存处理，加快查询速度
 * @RequestBody 接受对象
 */
@RestController
@Api(value = "文章接口", tags = "文章管理" )
public class ArticlesController {

    @Resource
    private ArticlesService articleService;
    /**
     * 首页文章列表
     * @LogAnnotation 加上此注解 代表要对此接口记录日志
     * @param pageParams 分页参数对象
     * @return
     */
    @ApiOperation(value = "首页文章列表",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;首页文章列表的接口")
    @PostMapping("/articles")
    @LogAnnotation(module="文章",operator="获取首页文章列表")
    @Cache(expire = 5 * 60 * 1000,name = "listArticle")
    public Result listArticle(@RequestBody PageParams pageParams){
        return articleService.listArticle(pageParams);
    }

    /**
     * 首页最热文章
     * @return
     */
    @ApiOperation(value = "首页最热文章",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;首页最热文章的接口")
    @PostMapping("/articles/hot")
    @LogAnnotation(module="文章",operator="获取首页最热文章")
    @Cache(expire = 5 * 60 * 1000,name = "hotArticle")
    public Result hotArticle(){
        Integer limit = 5;
        return articleService.hotArticle(limit);
    }

    /**
     * 首页最新文章
     * @return
     */
    @ApiOperation(value = "首页最新文章",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;首页最新文章的接口")
    @PostMapping("/articles/new")
    @LogAnnotation(module="文章",operator="获取首页最新文章")
    @Cache(expire = 5 * 60 * 1000,name = "newArticle")
    public Result newArticle(){
        Integer limit = 5;
        return articleService.newArticle(limit);
    }

    /**
     * 首页文章归档
     * @return
     */
    @ApiOperation(value = "首页文章归档",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;首页文章归档的接口")
    @PostMapping("/articles/listArchives")
    @LogAnnotation(module="文章",operator="获取首页文章归档")
    @Cache(expire = 5 * 60 * 1000,name = "listArchives")
    public Result listArchives(){
        Integer limit = 5;
        return articleService.listArchives();
    }



    @ApiOperation(value = "根据文章id查询文章详情",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据文章id查询文章详情的接口")
    @LogAnnotation(module="文章",operator="根据文章id查询文章详情")
    @PostMapping("/articles/view/{id}")
    public Result findArticleById(@PathVariable("id") Long articleId) {
        return articleService.findArticleById(articleId);
    }

    @ApiOperation(value = "发布文章",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;发布文章的接口")
    @LogAnnotation(module="文章",operator="发布文章")
    @PostMapping("/articles/publish")
    public Result publish(@RequestBody ArticleParam articleParam){
        return articleService.publish(articleParam);
    }



    /**
     * 修改文章评论数
     * @param articleId
     * @return
     */
    @ApiOperation(value = "修改文章评论数",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;修改文章评论数的接口")
    @LogAnnotation(module="文章",operator="修改文章评论数")
    @PostMapping("/articles/update")
    public void updatacommentCounts(Long articleId){
        articleService.updatacommentCounts(articleId);
    }

}
