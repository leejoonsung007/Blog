package com.springboot.blog.dao;

import com.springboot.blog.entity.NoteContent;
import com.springboot.blog.entity.NoteContentExample;
import java.util.List;

public interface NoteContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NoteContent record);

    int insertSelective(NoteContent record);

    List<NoteContent> selectByExampleWithBLOBs(NoteContentExample example);

    List<NoteContent> selectByExample(NoteContentExample example);

    NoteContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NoteContent record);

    int updateByPrimaryKeyWithBLOBs(NoteContent record);

    int updateByPrimaryKey(NoteContent record);
}