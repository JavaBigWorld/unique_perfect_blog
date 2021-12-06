package com.yxj.blog.blogback.controller;

import com.yxj.blog.blogback.entity.Result;
import com.yxj.blog.blogback.service.CommentManageService;
import com.yxj.blog.blogback.vo.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "评论管理接口", tags = "评论管理" )
@RestController
public class CommentManageController {

    @Resource
    private CommentManageService commentManageService;

    /**
     * 根据id删除评论
     * @param commentId
     * @return
     */
    @ApiOperation(value = "根据id删除评论的接口",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据id删除评论的接口")
    @PostMapping("/removeCommentById")
    public Result removeCommentById(Long commentId) {
        return commentManageService.removeCommentById(commentId);
    }

    /**
     * 评论管理
     * @return
     */
    @ApiOperation(value = "评论管理的接口",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;评论管理的接口")
    @PostMapping("/commentManagement")
    public Result commentManagement(@RequestBody PageVo pageVo) {
        return commentManageService.commentManagement(pageVo);
    }

    /**
     * 根据内容查询评论
     * @param content
     * @return
     */
    @ApiOperation(value = "根据内容查询评论的接口",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据内容查询评论的接口")
    @GetMapping("/selectCommentByContent")
    public Result selectCommentByContent(String content) {
        return commentManageService.selectCommentByContent(content);
    }
}
