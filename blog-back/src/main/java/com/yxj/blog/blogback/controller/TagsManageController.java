package com.yxj.blog.blogback.controller;


import com.yxj.blog.blogback.entity.Result;
import com.yxj.blog.blogback.service.TagsManageService;
import com.yxj.blog.blogback.vo.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "标签管理接口", tags = "标签管理" )
@RestController
public class TagsManageController {

    @Resource
    private TagsManageService tagsManageService;

    /**
     * 标签管理
     * @return
     */
    @ApiOperation(value = "标签管理的接口",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;标签管理的接口")
    @PostMapping("/tagsManagement")
    public Result tagsManagement(@RequestBody PageVo pageVo) {
        return tagsManageService.tagsManagement(pageVo);
    }

    /**
     * 根据标题名查询标签
     * @param name
     * @return
     */
    @ApiOperation(value = "根据标题名查询标签",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据标题名查询标签的接口")
    @GetMapping("/selectTagByName")
    public Result selectTagByName(String name) {
        return tagsManageService.selectTagByName(name);
    }

    /**
     * 根据id删除标签
     * @param tagId
     * @return
     */
    @ApiOperation(value = "根据id删除标签",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据id删除标签的接口")
    @PostMapping("/removeTagById")
    public Result removeTagById(Long tagId) {
        return tagsManageService.removeTagById(tagId);
    }
}
