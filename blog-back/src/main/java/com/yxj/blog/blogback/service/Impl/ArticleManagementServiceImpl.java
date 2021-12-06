package com.yxj.blog.blogback.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxj.blog.blogback.entity.Article;
import com.yxj.blog.blogback.entity.ArticleTag;
import com.yxj.blog.blogback.entity.Comment;
import com.yxj.blog.blogback.entity.Result;
import com.yxj.blog.blogback.mapper.*;
import com.yxj.blog.blogback.service.ArticleManagementService;
import com.yxj.blog.blogback.vo.ArticleManagementVo;
import com.yxj.blog.blogback.vo.PageVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ArticleManagementServiceImpl implements ArticleManagementService {

    @Resource
    private ArticlesMapper articlesMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private ArticleBodyMapper articleBodyMapper;
    @Resource
    private ArticleTagMapper articleTagMapper;
    @Resource
    private CommentMapper commentMapper;
    private Integer num;
    @Override
    public Result articleManagement(PageVo pageVo) {
        Integer start = (pageVo.getPageNum() - 1) * pageVo.getPageSize();
        Integer offset = pageVo.getPageSize();
        List<Article> articles = articlesMapper.selectData(start,offset);
        List<ArticleManagementVo> articleManagementVos = new ArrayList<>();
        for (Article article : articles) {
            ArticleManagementVo articleManagementVo = new ArticleManagementVo();
            articleManagementVo.setArticleId(article.getId().toString());
            articleManagementVo.setAuthor(sysUserMapper.selectById(article.getAuthorId()).getAccount());
            articleManagementVo.setTitle(article.getTitle());
            articleManagementVo.setCommentCounts(article.getCommentCounts());
            articleManagementVo.setViewCounts(article.getViewCounts());
            articleManagementVos.add(articleManagementVo);
        }
        List<Article> articles2 = articlesMapper.selectList(null);
        num = articles2.size();
        return Result.success(articleManagementVos,num);
    }



    @Override
    public Result selectArticleByName(String name) {
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.like("title",name);
        List<Article> articles = articlesMapper.selectList(articleQueryWrapper);
        List<ArticleManagementVo> articleManagementVos = new ArrayList<>();
        for (Article article : articles) {
            ArticleManagementVo articleManagementVo = new ArticleManagementVo();
            articleManagementVo.setArticleId(article.getId().toString());
            articleManagementVo.setAuthor(sysUserMapper.selectById(article.getAuthorId()).getAccount());
            articleManagementVo.setTitle(article.getTitle());
            articleManagementVo.setCommentCounts(article.getCommentCounts());
            articleManagementVo.setViewCounts(article.getViewCounts());
            articleManagementVos.add(articleManagementVo);
        }
        num = articles.size();
        return Result.success(articleManagementVos,num);
    }

    // delete from `article` where id = ?
    // delete from `article_body` where id = body_id
    // delete from `article_tag` where article_id = articleId
    // delete from `comment` where article_id = articleId
    @Override
    public Result removeArticleById(Long articleId) {
        try {
            QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
            articleQueryWrapper.eq("id",articleId);
            Article article = articlesMapper.selectOne(articleQueryWrapper);
            articlesMapper.deleteById(articleId);
            articleBodyMapper.deleteById(article.getBodyId());
            QueryWrapper<ArticleTag> articleTagQueryWrapper = new QueryWrapper<>();
            articleTagQueryWrapper.eq("article_id",articleId);
            articleTagMapper.delete(articleTagQueryWrapper);
            QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
            commentQueryWrapper.eq("article_id",articleId);
            commentMapper.delete(commentQueryWrapper);
            List<Article> articles2 = articlesMapper.selectList(null);
            num = articles2.size();
            return Result.success(null,num);
            // delete from `article` where id = ?
            // delete from `article_body` where id = body_id
           // delete from `article_tag` where article_id = articleId
           // delete from `comment` where article_id = articleId


        }catch (Exception e){
            return Result.fail(503,"服务器错误");
        }

    }
}
