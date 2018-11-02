package com.springboot.blog.service.impl;

import com.springboot.blog.dao.ArticleCommentMapper;
import com.springboot.blog.dao.CommentMapper;
import com.springboot.blog.dto.ArticleCommentDto;
import com.springboot.blog.entity.ArticleComment;
import com.springboot.blog.entity.ArticleCommentExample;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.CommentExample;
import com.springboot.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Comment service
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ArticleCommentMapper articleCommentMapper;

    //add a message
    @Override
    public void addComment(Comment comment) {
        commentMapper.insertSelective(comment);
    }

    //add a comment on a article
    @Override
    public void addArticleComment(ArticleCommentDto articleCommentDto) {
        // add comment data
        Comment comment = new Comment();
        comment.setIp(articleCommentDto.getIp());
        comment.setName(articleCommentDto.getName());
        comment.setEmail(articleCommentDto.getEmail());
        comment.setContent(articleCommentDto.getContent().replaceAll("<script>","").replaceAll("</script>", ""));
        addComment(comment);
        // tbl_article_comment
        CommentExample example = new CommentExample();
        example.setOrderByClause("id desc");
        Long lastestCommentId = commentMapper.selectByExample(example).get(0).getId();
        ArticleComment articleComment = new ArticleComment();
        articleComment.setCommentId(lastestCommentId);
        articleComment.setArticleId(articleCommentDto.getArticleId());
        articleCommentMapper.insertSelective(articleComment);
    }

    //delete a message based on its id
    @Override
    public void deleteCommentById(Long id) {
        Comment comment = commentMapper.selectByPrimaryKey(id);
        comment.setIsEffective(false);
        commentMapper.updateByPrimaryKeySelective(comment);
    }

   //delete a article comment
    @Override
    public void deleteArticleCommentById(Long id) {
        // 设置ArticleComment表中的字段为false
        ArticleComment articleComment = articleCommentMapper.selectByPrimaryKey(id);
        articleComment.setIsEffective(false);
        articleCommentMapper.updateByPrimaryKeySelective(articleComment);
        // 删除Comment表中对应的数据
        deleteCommentById(articleComment.getCommentId());
    }

    //list all message
    @Override
    public List<Comment> listAllComment() {
        CommentExample example = new CommentExample();
        return commentMapper.selectByExample(example);
    }

    //list all comments of a article
    @Override
    public List<ArticleCommentDto> listAllArticleCommentById(Long id) {
        List<ArticleCommentDto> comments = new ArrayList<>();
        ArticleCommentExample example = new ArticleCommentExample();
        example.or().andArticleIdEqualTo(id);
        List<ArticleComment> articleComments = articleCommentMapper.selectByExample(example);
        // comment information
        for (ArticleComment articleComment : articleComments) {
            if (true == articleComment.getIsEffective()) {
                ArticleCommentDto articleCommentDto = new ArticleCommentDto();
                articleCommentDto.setArticleCommentId(articleComment.getId());
                articleCommentDto.setArticleId(articleComment.getArticleId());
                articleCommentDto.setCreateBy(articleComment.getCreateBy());
                // search corresponding comment
                Comment comment = commentMapper.selectByPrimaryKey(articleComment.getCommentId());
                articleCommentDto.setId(comment.getId());
                articleCommentDto.setContent(comment.getContent());
                articleCommentDto.setEmail(comment.getEmail());
                articleCommentDto.setIp(comment.getIp());
                articleCommentDto.setName(comment.getName());
                comments.add(articleCommentDto);
            }
        }
        return comments;
    }

}
