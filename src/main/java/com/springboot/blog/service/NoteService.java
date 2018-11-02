package com.springboot.blog.service;

import com.springboot.blog.dto.ArticleWithPictureDto;
import com.springboot.blog.dto.NoteDto;
import com.springboot.blog.dto.NoteWithPictureDto;
import com.springboot.blog.entity.NotePicture;

import java.util.List;

public interface NoteService {

    void addNote(NoteDto noteDto);

    void deleteNoteById(Long id);

    void updateNote(NoteDto noteDto);

    NoteDto getOneById(Long id);

    NotePicture getPictureByNoteId(Long id);

    List<NoteWithPictureDto> listAll();

    List<NoteWithPictureDto> listLatest();

    List<NoteDto> listAllNotes();

}
