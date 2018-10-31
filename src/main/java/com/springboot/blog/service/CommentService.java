package com.springboot.blog.service;

import com.springboot.blog.dto.ArticleCommentDto;
import com.springboot.blog.entity.Comment;

import java.util.List;

//Comment Service
public interface CommentService {
    void addComment(Comment comment);

    void addArticleComment(ArticleCommentDto articleCommentDto);

    void deleteCommentById(Long id);

    void deleteArticleCommentById(Long id);

    List<Comment> listAllComment();

    List<ArticleCommentDto> listAllArticleCommentById(Long id);
}
