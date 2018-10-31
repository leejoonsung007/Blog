package com.springboot.blog.service;

import com.springboot.blog.dto.ArticleCategoryDto;
import com.springboot.blog.entity.ArticleCategory;
import com.springboot.blog.entity.CategoryInfo;

import java.util.List;

//Category Service
public interface CategoryService {
    void addCategory(CategoryInfo categoryInfo);

    void deleteCategoryById(Long id);

    void updateCategory(CategoryInfo categoryInfo);

    void updateArticleCategory(ArticleCategory articleCategory);

    CategoryInfo getOneById(Long id);

    List<CategoryInfo> listAllCategory();

    ArticleCategoryDto getCategoryByArticleId(Long id);
}
