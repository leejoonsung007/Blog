package com.springboot.blog.dao;

import com.springboot.blog.entity.NoteInfo;
import com.springboot.blog.entity.NoteInfoExample;
import java.util.List;

public interface NoteInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NoteInfo record);

    int insertSelective(NoteInfo record);

    List<NoteInfo> selectByExample(NoteInfoExample example);

    NoteInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NoteInfo record);

    int updateByPrimaryKey(NoteInfo record);
}