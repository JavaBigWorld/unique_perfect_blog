package com.yxj.blog.blogback.controller;


import com.yxj.blog.blogback.entity.Result;
import com.yxj.blog.blogback.service.ArticleManagementService;
import com.yxj.blog.blogback.vo.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Transactional
@RestController
@Api(value = "文章管理接口", tags = "文章管理" )
public class ArticleManagementController {

    @Resource
    private ArticleManagementService articleManagementService;


    /**
     * 文章管理
     * @return
     */
    @ApiOperation(value = "文章管理",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;文章管理的接口")
    @PostMapping("/articleManagement")
    public Result articleManagement(@RequestBody PageVo pageVo) {
        return articleManagementService.articleManagement(pageVo);
    }

    /**
     * 根据文章id删除文章
     * @param articleId
     * @return
     */
    @ApiOperation(value = "根据文章id删除文章",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据文章id删除文章的接口")
    @PostMapping("/removeArticleById")
    public Result removeArticleById(Long articleId) {
        return articleManagementService.removeArticleById(articleId);
    }


    /**
     * 根据标题查询文章
     * @param name
     * @return
     */
    @ApiOperation(value = "根据标题查询文章",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据标题查询文章的接口")
    @GetMapping("/selectArticleByName")
    public Result selectAriticleByName(String name) {
        return articleManagementService.selectArticleByName(name);
    }
}