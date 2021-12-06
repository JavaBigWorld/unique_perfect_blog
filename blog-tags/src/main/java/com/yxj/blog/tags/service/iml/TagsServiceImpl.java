package com.yxj.blog.tags.service.iml;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yxj.blog.common.entity.Tag;
import com.yxj.blog.common.vo.Result;
import com.yxj.blog.common.vo.TagVo;
import com.yxj.blog.tags.mapper.TagsMapper;
import com.yxj.blog.tags.service.TagsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 从前慢
 * @Transactional 事务注解
 * @Service 生成类实例交个ioc容器管理
 * @Resource 生成的类实例交给SpringIOC容器管理
 * @Override 方法重写标识
 */
@Transactional
@Service
public class TagsServiceImpl implements TagsService {

    @Resource
    private TagsMapper tagsMapper;

    public TagVo copy(Tag tag){
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag,tagVo);
        tagVo.setId(String.valueOf(tag.getId()));
        return tagVo;
    }
    public List<TagVo> copyList(List<Tag> tagList){
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;
    }

    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
        /**
         * mybatisplus 无法进行多表查询
         */
        List<Tag> tags = tagsMapper.findTagsByArticleId(articleId);
        return copyList(tags);
    }

    @Override
    public Result hot(Integer limit) {
        /**
         * 1 标签所拥有的文章数量最多 最热标签
         * 2 查询 根据tag_id 分组计数，从大到小取前limit个
         */
        List<Long> tagIds = tagsMapper.findHotsTagIds(limit);
        /**
         * 需求的是tagId和tagName Tag对象
         */
        if (CollectionUtils.isEmpty(tagIds)){
            return Result.success(Collections.emptyList());
        }
        List<Tag>  tagList = tagsMapper.findTagsByTagIds(tagIds);

        return Result.success(tagList);
    }

    @Override
    public Result findAll() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId,Tag::getTagName);
        List<Tag> tagList = tagsMapper.selectList(queryWrapper);
        return Result.success(copyList(tagList));
    }

    @Override
    public Result findAllDetail() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        List<Tag> tagList = tagsMapper.selectList(queryWrapper);
        return Result.success(copyList(tagList));
    }

    @Override
    public Result findAllDetailById(Long id) {
        Tag tag = tagsMapper.selectById(id);
        return Result.success(copy(tag));
    }


}
