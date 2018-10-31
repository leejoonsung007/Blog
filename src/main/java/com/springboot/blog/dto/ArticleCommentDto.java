package com.springboot.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

// get comment - /api/comment/article/{id}
//join tbl_comment和tbl_article_comment

public class ArticleCommentDto {
    // tbl_comment
    private Long id;
    private String content;
    private String name;
    private String email;
    private String ip;
    private Date createBy;

    // tbl_article_comment
    private Long articleCommentId;
    private Long articleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getArticleCommentId() {
        return articleCommentId;
    }

    public void setArticleCommentId(Long articleCommentId) {
        this.articleCommentId = articleCommentId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }
}
