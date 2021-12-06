package com.yxj.blog.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yxj.blog.comment.feign.ArticleClient;
import com.yxj.blog.comment.feign.UserClient;
import com.yxj.blog.comment.mapper.CommentMapper;
import com.yxj.blog.comment.service.CommentsService;
import com.yxj.blog.comment.utils.UserThreadLocal;
import com.yxj.blog.common.entity.Comment;
import com.yxj.blog.common.entity.SysUser;
import com.yxj.blog.common.vo.CommentVo;
import com.yxj.blog.common.vo.Result;
import com.yxj.blog.common.vo.UserVo;
import com.yxj.blog.common.vo.params.CommentParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 从前慢
 * @Service 生成类实例交个ioc容器管理
 * @Transactional 事务注解
 * @Resource 生成的类实例交给SpringIOC容器管理
 * @Override 方法重写标识
 */
@Service
@Transactional
public class CommentsServiceImpl implements CommentsService {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private UserClient userClient;

    @Resource
    private ArticleClient articleClient;

    /**
     * 1 根据id查询评论列表 从comment表中查询
     * 2 根据作者的id查询作者的信息
     * 3 判断如果level=1要去查询它有没有子评论
     * 4 如果有根据评论id进行查询（parent_id）
     * @param id
     * @return
     */
    @Override
    public Result commentsByArticleId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, id);
        queryWrapper.eq(Comment::getLevel,1);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        List<CommentVo> commentVoList = copyList(comments);
        return Result.success(commentVoList);
    }

    @Override
    public Result comment(CommentParam commentParam) {
        SysUser sysUser = UserThreadLocal.get();
        Comment comment = new Comment();
        comment.setArticleId(commentParam.getArticleId());
        comment.setAuthorId(sysUser.getId());
        comment.setContent(commentParam.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        Long parent = commentParam.getParent();
        if (null == parent || parent == 0) {
            comment.setLevel(1);
        } else {
            comment.setLevel(2);
        }
        comment.setParentId(parent == null ? 0 : parent);
        Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        commentMapper.insert(comment);
        articleClient.updatacommentCounts(commentParam.getArticleId());

        return Result.success(null);
    }

    private List<CommentVo> copyList(List<Comment> comments) {
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment comment : comments) {
            commentVoList.add(copy(comment));
        }
        return commentVoList;
    }

    private CommentVo copy(Comment comment) {
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment,commentVo);
        /**
         * String.valueOf()有效防止空指针异常
         */
        commentVo.setId(String.valueOf(comment.getId()));
        /**
         * 作者信息
         */
        Long authorId = comment.getAuthorId();
        /**
         * 远程调用user服务
         */
        UserVo userVo = userClient.findUserVoById(authorId);
        commentVo.setAuthor(userVo);
        /**
         * 子评论
         */
        Integer level = comment.getLevel();
        if (1 == level) {
            Long id = comment.getId();
            List<CommentVo> commentVoList = findCommentsByParentId(id);
            commentVo.setChildrens(commentVoList);
        }
        /**
         * to user 给谁评论
         */
        if (level > 1) {
            Long toUid = comment.getToUid();
            /**
             * 根据被评论作者的id查询被作者的信息
             * 远程调用用户服务
             */
            UserVo toUserVo = userClient.findUserVoById(toUid);
            commentVo.setToUser(toUserVo);
        }
        return commentVo;
    }

    private List<CommentVo> findCommentsByParentId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId,id);
        queryWrapper.eq(Comment::getLevel,2);
        return copyList(commentMapper.selectList(queryWrapper));
    }
}
