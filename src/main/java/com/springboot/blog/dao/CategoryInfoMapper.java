package com.springboot.blog.dao;

import com.springboot.blog.entity.CategoryInfo;
import com.springboot.blog.entity.CategoryInfoExample;

import java.util.List;

public interface CategoryInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CategoryInfo record);

    int insertSelective(CategoryInfo record);

    List<CategoryInfo> selectByExample(CategoryInfoExample example);

    CategoryInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CategoryInfo record);

    int updateByPrimaryKey(CategoryInfo record);
}