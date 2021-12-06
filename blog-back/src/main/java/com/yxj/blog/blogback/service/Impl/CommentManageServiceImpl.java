package com.yxj.blog.blogback.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxj.blog.blogback.entity.Article;
import com.yxj.blog.blogback.entity.Result;
import com.yxj.blog.blogback.mapper.ArticlesMapper;
import com.yxj.blog.blogback.mapper.CommentMapper;
import com.yxj.blog.blogback.mapper.SysUserMapper;
import com.yxj.blog.blogback.service.CommentManageService;
import com.yxj.blog.blogback.vo.CommentManageVo;
import com.yxj.blog.blogback.vo.PageVo;
import com.yxj.blog.blogback.entity.Comment;
import com.yxj.blog.blogback.entity.SysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentManageServiceImpl implements CommentManageService {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private ArticlesMapper articlesMapper;

    private Integer num;
    @Override
    public Result removeCommentById(Long commentId) {
        Article article = articlesMapper.selectOne(new QueryWrapper<Article>().eq("id", commentMapper.selectOne(new QueryWrapper<Comment>().eq("id", commentId)).getArticleId()));
        article.setCommentCounts(article.getCommentCounts() - 1);
        articlesMapper.updateById(article);
        commentMapper.deleteById(commentId);
        List<Comment> comments = commentMapper.selectList(null);
        num = comments.size();
        return Result.success(null,num);
    }

    @Override
    public Result commentManagement(PageVo pageVo) {
        Integer start = (pageVo.getPageNum() - 1) * pageVo.getPageSize();
        Integer offset = pageVo.getPageSize();
        List<Comment> comments = commentMapper.selectData(start,offset);
        ArrayList<CommentManageVo> commentManageVos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentManageVo commentManageVo = new CommentManageVo();
            commentManageVo.setId(comment.getId().toString());
            commentManageVo.setContent(comment.getContent());
            SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("id", comment.getAuthorId()));
            commentManageVo.setAuthor(sysUser.getAccount());
            Article article = articlesMapper.selectOne(new QueryWrapper<Article>().eq("id", comment.getArticleId()));
            commentManageVo.setArticle(article.getTitle());
            commentManageVos.add(commentManageVo);
        }
        List<Comment> comments2 = commentMapper.selectList(null);
        num = comments2.size();
        return Result.success(commentManageVos,num);
    }

    @Override
    public Result selectCommentByContent(String content) {
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.like("content",content);
        List<Comment> comments = commentMapper.selectList(commentQueryWrapper);
        ArrayList<CommentManageVo> commentManageVos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentManageVo commentManageVo = new CommentManageVo();
            commentManageVo.setId(comment.getId().toString());
            commentManageVo.setContent(comment.getContent());
            SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("id", comment.getAuthorId()));
            commentManageVo.setAuthor(sysUser.getAccount());
            Article article = articlesMapper.selectOne(new QueryWrapper<Article>().eq("id", comment.getArticleId()));
            commentManageVo.setArticle(article.getTitle());
            commentManageVos.add(commentManageVo);
        }
        num = comments.size();
        return Result.success(commentManageVos,num);
    }
}
