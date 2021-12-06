package com.yxj.blog.ariticles.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxj.blog.ariticles.feign.CategoryClient;
import com.yxj.blog.ariticles.feign.TagsClient;
import com.yxj.blog.ariticles.feign.UserClient;
import com.yxj.blog.ariticles.mapper.ArticleBodyMapper;
import com.yxj.blog.ariticles.mapper.ArticleTagMapper;
import com.yxj.blog.ariticles.mapper.ArticlesMapper;
import com.yxj.blog.ariticles.service.ArticlesService;
import com.yxj.blog.ariticles.service.ThreadService;
import com.yxj.blog.ariticles.utils.UserThreadLocal;
import com.yxj.blog.common.dos.Archives;
import com.yxj.blog.common.entity.Article;
import com.yxj.blog.common.entity.ArticleBody;
import com.yxj.blog.common.entity.ArticleTag;
import com.yxj.blog.common.entity.SysUser;
import com.yxj.blog.common.vo.ArticleBodyVo;
import com.yxj.blog.common.vo.ArticleVo;
import com.yxj.blog.common.vo.Result;
import com.yxj.blog.common.vo.TagVo;
import com.yxj.blog.common.vo.params.ArticleParam;
import com.yxj.blog.common.vo.params.PageParams;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 从前慢
 * @Transactional 事务注解
 * @Service 生成类实例交个ioc容器管理
 * @Resource 生成的类实例交给SpringIOC容器管理
 * @Override 方法重写标识
 */
@Transactional
@Service
public class ArticlesServiceImpl implements ArticlesService {


    @Resource
    ArticlesMapper articlesMapper;

    @Resource
    TagsClient tagsClient;

    @Resource
    UserClient userClient;

    @Resource
    CategoryClient categoryClient;

    @Resource
    ThreadService threadService;

    @Resource
    private ArticleBodyMapper articleBodyMapper;

    @Resource
    private ArticleTagMapper articleTagMapper;

    @Override
    public Result listArticle(PageParams pageParams) {
        Page<Article> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        IPage<Article> articleIPage = articlesMapper.listArticle(page,
                pageParams.getCategoryId(),
                pageParams.getTagId(),
                pageParams.getYear(),
                pageParams.getMonth());
        List<Article> records = articleIPage.getRecords();
        return Result.success(copyList(records,Boolean.TRUE,Boolean.TRUE));
    }

    @Override
    public Result hotArticle(Integer limit) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(Article::getId,Article::getTitle)
                .orderByDesc(Article::getViewCounts)
                .last("limit " + limit);

        List<Article> articles = articlesMapper.selectList(lambdaQueryWrapper);

        return  Result.success(copyList(articles,Boolean.FALSE,Boolean.FALSE));
    }

    @Override
    public Result newArticle(Integer limit) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .select(Article::getId,Article::getTitle)
                .orderByDesc(Article::getCreateDate)
                .last("limit " + limit);

        List<Article> articles = articlesMapper.selectList(lambdaQueryWrapper);

        return  Result.success(copyList(articles,Boolean.FALSE,Boolean.FALSE));
    }

    @Override
    public Result listArchives() {
        List<Archives>  archives = articlesMapper.listArchives();
        return Result.success(archives);
    }

    @Override
    public Result findArticleById(Long articleId) {
        /**
         * 1 根据id查询文章信息
         * 2 根据bodyId和categorid去做关联查询
         */
        Article article = articlesMapper.selectById(articleId);
        ArticleVo articleVo = copy(article, Boolean.TRUE, Boolean.TRUE,Boolean.TRUE,Boolean.TRUE);
        /**
         * 查看完文章后，新增阅读树
         * 查看完文章后吗，本应该直接返回数据的，这时候做了一个更新操作，更新时加写锁，阻塞其他的读操作，性能比较低
         * 更新 增加了此次接口的耗时，如果一旦更新出问题，不能影响文章的读操作
         * 线程池 可以把更新操作 扔到线程池中去执行，和主线程就不想关了
         */
        System.out.println("-----------------------------------------------------");
        System.out.println(articleVo);
        threadService.updateArticleViewCount(articlesMapper,article);
        return Result.success(articleVo);
    }

    @Override
    public Result publish(ArticleParam articleParam) {
        /**
         * 1 发布文章 目的 构建Article对象
         * 2 作者id 当前的登录用户
         * 3 标签 要将标签加入到关联列表当中
         * 4 body 内容存储
         */
        SysUser sysUser = UserThreadLocal.get();
        Article article = new Article();
        article.setAuthorId(sysUser.getId());
        article.setWeight(Article.Article_Common);
        article.setViewCounts(0);
        article.setTitle(articleParam.getTitle());
        article.setSummary(articleParam.getSummary());
        article.setCommentCounts(0);
        article.setCreateDate(System.currentTimeMillis());

        article.setCategoryId(Long.parseLong(articleParam.getCategory().getId()));
        /**
         * 插入之后，会生成一个文章id
         */
        articlesMapper.insert(article);
        List<TagVo> tags = articleParam.getTags();
        if (tags != null) {
            for (TagVo tag : tags) {
                Long articleId = article.getId();
                ArticleTag articleTag = new ArticleTag();
                articleTag.setTagId(Long.parseLong(tag.getId()));
                articleTag.setArticleId(articleId);
                articleTagMapper.insert(articleTag);
            }

        }

        ArticleBody articleBody = new ArticleBody();
        articleBody.setArticleId(article.getId());
        articleBody.setContent(articleParam.getBody().getContent());
        articleBody.setContentHtml(articleParam.getBody().getContentHtml());
        articleBodyMapper.insert(articleBody);
        article.setBodyId(articleBody.getId());
        articlesMapper.updateById(article);
        Map<String,String> map = new HashMap<>();
        map.put("id",String.valueOf(article.getId()));
        return Result.success(map);
    }

    @Override
    public void updatacommentCounts(Long articleId) {
        Article article = articlesMapper.selectById(articleId);
        article.setCommentCounts(article.getCommentCounts() + 1);
        articlesMapper.updateById(article);
    }

    private List<ArticleVo> copyList(List<Article> records,Boolean isTag,Boolean isAuthor) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {

            articleVoList.add(copy(record,isTag,isAuthor,false,false));
        }
        return articleVoList;
    }

    private List<ArticleVo> copyList(List<Article> records,Boolean isTag,Boolean isAuthor,Boolean isBody,Boolean isCategory) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {

            articleVoList.add(copy(record,isTag,isAuthor,isBody,isCategory));
        }
        return articleVoList;
    }

    /**
     * 并不是所有的接口都需要作者信息，标签信息，文章内容，分类等，为了加快查询效率，可以
     * 对这些进行筛选再查询
     * @param article
     * @param isTag
     * @param isAuthor
     * @param isBody
     * @param isCategory
     * @return
     */
    private ArticleVo copy(Article article,Boolean isTag,Boolean isAuthor,Boolean isBody,Boolean isCategory){
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article,articleVo);
        /**
         * String.valueOf(article.getId()) 防止空指针异常
         */
        articleVo.setId(String.valueOf(article.getId()));
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        if (isTag){
            Long articleId = article.getId();
            /**
             * 远程调用blog-tags服务，通过文章id查询文章的标签信息
             */
            articleVo.setTags(tagsClient.findTagsByArticleId(articleId));
        }
        if (isAuthor){
            Long authorId = article.getAuthorId();
            /**
             * 远程调用blog-user服务，通过作者id查询文章的作者信息
             */
            SysUser user = userClient.findUserById(authorId);
            articleVo.setAuthor(user.getNickname());
            articleVo.setAvatar(user.getAvatar());
        }

        if (isBody) {
            Long bodyId = article.getBodyId();
            articleVo.setBody(findArticleBodyById(bodyId));
        }

        /**
         * 分类
         */
        if (isCategory) {
            Long categoryId = article.getCategoryId();
            articleVo.setCategory(categoryClient.findCategoryById(categoryId));
        }
        return articleVo;

    }


    private ArticleBodyVo findArticleBodyById(Long bodyId) {
        ArticleBody articleBody = articleBodyMapper.selectById(bodyId);
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());
        return articleBodyVo;
    }
}
