package com.springboot.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

//  get article information - /api/article/{id}
//join tbl_article_info, tbl_article_content, tbl_article_category, tbl_category_info,
// tbl_article_picture

public class ArticleDto {

    // tbl_article_info
    private Long id;
    private String title;
    private String summary;
    private Boolean isTop;
    private Integer traffic;
    private Date createBy;

    // tbl_article_content
    private Long articleContentId;
    private String content;

    // tbl_category_info
    private Long categoryId;
    private String categoryName;
    private Byte categoryNumber;

    // tbl_article_category
    private Long articleCategoryId;

    // tbl_article_picture
    private Long articlePictureId;
    private String pictureUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+1")
    public Date getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public Integer getTraffic() {
        return traffic;
    }

    public void setTraffic(Integer traffic) {
        this.traffic = traffic;
    }

    public Long getArticleContentId() {
        return articleContentId;
    }

    public void setArticleContentId(Long articleContentId) {
        this.articleContentId = articleContentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Byte getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(Byte categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public Long getArticlePictureId() {
        return articlePictureId;
    }

    public void setArticlePictureId(Long articlePictureId) {
        this.articlePictureId = articlePictureId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Long getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(Long articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }
}
