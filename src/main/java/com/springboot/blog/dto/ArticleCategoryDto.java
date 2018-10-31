package com.springboot.blog.dto;

// get category - /admin/category/{id}
// join tbl_article_category和tbl_category_info
public class ArticleCategoryDto {

    //  tbl_article_category
    private Long id;
    private Long categoryId;
    private Long articleId;

    // tbl_category_info
    private String name;
    private Byte number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getNumber() {
        return number;
    }

    public void setNumber(Byte number) {
        this.number = number;
    }
}
