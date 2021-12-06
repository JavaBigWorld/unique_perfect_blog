package com.yxj.blog.common.vo.params;

import com.yxj.blog.common.vo.CategoryVo;
import com.yxj.blog.common.vo.TagVo;
import lombok.Data;

import java.util.List;

@Data
public class ArticleParam {

    private Long id;

    private ArticleBodyParam body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;
}
