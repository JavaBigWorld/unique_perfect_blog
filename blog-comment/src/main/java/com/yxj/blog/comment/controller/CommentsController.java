package com.yxj.blog.comment.controller;

import com.yxj.blog.comment.service.CommentsService;
import com.yxj.blog.common.vo.Result;
import com.yxj.blog.common.vo.params.CommentParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 从前慢
 * @RestController 返回json数据
 * @Resource 生成的类实例交给SpringIOC容器管理
 * @GetMapping Get请求
 * @PostMapping Post请求
 *
 */
@RestController
@Api(value = "评论接口", tags = "评论管理" )
public class CommentsController {

    @Resource
    CommentsService commentsService;


    /**
     * 根据文章id查询所有的评论列表
     * @param id
     * @return
     */
    @GetMapping("/comments/article/{id}")
    @ApiOperation(value = "根据文章id查询所有的评论列表",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据文章id查询所有的评论列表的接口")
    public Result comment(@PathVariable("id")Long id ) {

        return commentsService.commentsByArticleId(id);
    }

    /**
     * 发表评论
     */
    @PostMapping("/comments/create/change")
    @ApiOperation(value = "发表评论",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;发表评论的接口")
    public Result comment(@RequestBody CommentParam commentParam) {
        return commentsService.comment(commentParam);
    }

}
