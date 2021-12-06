package com.yxj.blog.blogback.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxj.blog.blogback.entity.Result;
import com.yxj.blog.blogback.entity.Tag;
import com.yxj.blog.blogback.mapper.TagsMapper;
import com.yxj.blog.blogback.vo.PageVo;
import com.yxj.blog.blogback.vo.TagsManageVo;
import com.yxj.blog.blogback.service.TagsManageService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagsManageServiceImpl implements TagsManageService {

    @Resource
    private TagsMapper tagsMapper;
    private Integer num;

    @Override
    public Result removeTagById(Long tagId) {
        tagsMapper.deleteById(tagId);
        List<Tag> tags = tagsMapper.selectList(null);
        num = tags.size();
        return Result.success(null,num);
    }

    @Override
    public Result tagsManagement(PageVo pageVo) {
        Integer start = (pageVo.getPageNum() - 1) * pageVo.getPageSize();
        Integer offset = pageVo.getPageSize();
        List<Tag> tags = tagsMapper.selectData(start,offset);
        List<TagsManageVo> tagsManageVos = new ArrayList<>();
        for (Tag tag : tags) {
            TagsManageVo tagsManageVo = new TagsManageVo();
            BeanUtils.copyProperties(tag,tagsManageVo);
            tagsManageVos.add(tagsManageVo);
        }
        List<Tag> tags2 = tagsMapper.selectList(null);
        num = tags2.size();
        return Result.success(tagsManageVos,num);
    }

    @Override
    public Result selectTagByName(String name) {
        QueryWrapper<Tag> tagQueryWrapper = new QueryWrapper<>();
        tagQueryWrapper.like("tag_name",name);
        List<Tag> tags = tagsMapper.selectList(tagQueryWrapper);
        ArrayList<TagsManageVo> tagsManageVos = new ArrayList<>();
        for (Tag tag : tags) {
            TagsManageVo tagsManageVo = new TagsManageVo();
            BeanUtils.copyProperties(tag,tagsManageVo);
            tagsManageVos.add(tagsManageVo);
        }
        num = tags.size();
        return Result.success(tagsManageVos,num);
    }
}
