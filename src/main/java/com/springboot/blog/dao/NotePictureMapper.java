package com.springboot.blog.dao;

import com.springboot.blog.entity.NotePicture;
import com.springboot.blog.entity.NotePictureExample;
import java.util.List;

public interface NotePictureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NotePicture record);

    int insertSelective(NotePicture record);

    List<NotePicture> selectByExample(NotePictureExample example);

    NotePicture selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NotePicture record);

    int updateByPrimaryKey(NotePicture record);
}