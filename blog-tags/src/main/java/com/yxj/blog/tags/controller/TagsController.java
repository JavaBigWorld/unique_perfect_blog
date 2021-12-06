package com.yxj.blog.tags.controller;

import com.yxj.blog.common.vo.Result;
import com.yxj.blog.common.vo.TagVo;
import com.yxj.blog.tags.service.TagsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 从前慢
 * @RestController 返回json数据
 * @Slf4j 打印日志
 * @Resource 生成的类实例交给SpringIOC容器管理
 * @GetMapping Get请求
 * @Api swagger类上接口说明
 * @ApiOperation swagger方法上接口说明
 */


@RestController
@Api(value = "标签接口", tags = "标签管理" )
@Slf4j
public class TagsController {

    @Resource
    private TagsService tagsService;


    /**
     * 通过文章id查询文章所对应的标签信息
     * @param articleId
     * @return
     */
    @GetMapping("/tags/findTags")
    @ApiOperation(value = "根据文章id获取标签信息",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据文章id获取标签信息的接口")
    List<TagVo> findTagsByArticleId(Long articleId){
        return tagsService.findTagsByArticleId(articleId);
    }

    /**
     * 查询最热标签
     * @return
     */
    @GetMapping("/tags/hot")
    @ApiOperation(value = "查询最热标签",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;查询最热标签的接口")
    public Result hot(){
        /**
         * 显示最热标签6条
         */
        Integer limit = 6;
        return tagsService.hot(limit);
    }


    @ApiOperation(value = "查询所有的文章标签",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;查询所有的文章标签的接口")
    @GetMapping("/tags")
    public Result findAll() {
        return tagsService.findAll();
    }

    @ApiOperation(value = "查询所有的文章标签详细内容",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;查询所有的文章标签详细内容的接口")
    @GetMapping("/tags/detail")
    public Result findAllDetail(){
        return tagsService.findAllDetail();
    }

    @ApiOperation(value = "根据标签id查询标签详细信息",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;根据标签id查询标签详细信息的接口")
    @GetMapping("/tags/detail/{id}")
    public Result findAllDetailById(@PathVariable("id") Long id)
    {
        return tagsService.findAllDetailById(id);
    }


}
