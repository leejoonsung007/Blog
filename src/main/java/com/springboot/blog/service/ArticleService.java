package com.springboot.blog.service;

import com.springboot.blog.dto.ArticleDto;
import com.springboot.blog.dto.ArticleWithPictureDto;
import com.springboot.blog.entity.ArticlePicture;

import java.util.List;

// Article Service
public interface ArticleService {

    void addArticle(ArticleDto articleDto);

    void deleteArticleById(Long id);

    void updateArticle(ArticleDto articleDto);

    void updateArticleCategory(Long articleId, Long categoryId);

    ArticleDto getOneById(Long id);

    ArticlePicture getPictureByArticleId(Long id);

    List<ArticleWithPictureDto> listAll();

    List<ArticleWithPictureDto> listByCategoryId(Long id);

    List<ArticleWithPictureDto> listLastest();
}
