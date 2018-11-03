package com.springboot.blog.service.impl;

import com.springboot.blog.dao.ArticleCategoryMapper;
import com.springboot.blog.dao.CategoryInfoMapper;
import com.springboot.blog.dto.ArticleCategoryDto;
import com.springboot.blog.entity.ArticleCategory;
import com.springboot.blog.entity.ArticleCategoryExample;
import com.springboot.blog.entity.CategoryInfo;
import com.springboot.blog.entity.CategoryInfoExample;
import com.springboot.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//category service
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryInfoMapper categoryInfoMapper;
    @Autowired
    ArticleCategoryMapper articleCategoryMapper;

    //add a category info
    @Override
    public void addCategory(CategoryInfo categoryInfo) {
        categoryInfoMapper.insertSelective(categoryInfo);
    }

    //delete a category by id
    @Override
    public void deleteCategoryById(Long id) {
        // delete ArticleCategory
        ArticleCategoryExample example = new ArticleCategoryExample();
        example.or().andCategoryIdEqualTo(id);
        List<ArticleCategory> categories = articleCategoryMapper.selectByExample(example);
        for (ArticleCategory articleCategory : categories) {
            articleCategoryMapper.deleteByPrimaryKey(articleCategory.getId());
        }
        // delete CategoryInfo
        categoryInfoMapper.deleteByPrimaryKey(id);
    }


    //update article category
    @Override
    public void updateArticleCategory(ArticleCategory articleCategory) {
        articleCategoryMapper.updateByPrimaryKeySelective(articleCategory);
    }

    // update a category
    @Override
    public void updateCategory(CategoryInfo categoryInfo) {
        categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
    }

    //get a category info by id
    @Override
    public CategoryInfo getOneById(Long id) {
        CategoryInfoExample example = new CategoryInfoExample();
        example.or().andIdEqualTo(id);
        List<CategoryInfo> categoryInfos = categoryInfoMapper.selectByExample(example);
        return categoryInfos.get(0);
    }

    // list all category info
    @Override
    public List<CategoryInfo> listAllCategory() {
        CategoryInfoExample example = new CategoryInfoExample();
        return categoryInfoMapper.selectByExample(example);
    }

    // getCategoryByArticleId
    @Override
    public ArticleCategoryDto getCategoryByArticleId(Long id) {
        ArticleCategoryDto articleCategoryDto = new ArticleCategoryDto();
        // get tbl_article_category data
        ArticleCategoryExample example = new ArticleCategoryExample();
        example.or().andArticleIdEqualTo(id);
        List<ArticleCategory> articleCategories = articleCategoryMapper.selectByExample(example);
        ArticleCategory articleCategory = articleCategories.get(0);
        articleCategoryDto.setArticleId(articleCategory.getArticleId());
        articleCategoryDto.setId(articleCategory.getId());
        articleCategoryDto.setCategoryId(articleCategory.getCategoryId());
        // get category info
        CategoryInfo categoryInfo = getOneById(articleCategory.getCategoryId());
        articleCategoryDto.setName(categoryInfo.getName());
        articleCategoryDto.setNumber(categoryInfo.getNumber());
        return articleCategoryDto;
    }

}
