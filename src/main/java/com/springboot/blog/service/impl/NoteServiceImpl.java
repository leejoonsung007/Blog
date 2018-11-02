package com.springboot.blog.service.impl;

import com.springboot.blog.dao.*;
import com.springboot.blog.dto.NoteDto;
import com.springboot.blog.dto.NoteWithPictureDto;
import com.springboot.blog.entity.*;
import com.springboot.blog.service.NoteService;
import com.springboot.blog.util.Markdown2HtmlUtil;
import org.hibernate.validator.cfg.defs.NotEmptyDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteInfoMapper noteInfoMapper;

    @Autowired
    private NotePictureMapper notePictureMapper;

    @Autowired
    private NoteContentMapper noteContentMapper;

    private static byte MAX_LASTEST_NOTE_COUNT = 1;

    //add a note
    @Override
    public void addNote(NoteDto noteDto) {
        // set note info - title/summary
        NoteInfo noteInfo = new NoteInfo();
        noteInfo.setTitle(noteDto.getTitle());
        noteInfoMapper.insertSelective(noteInfo);
        //get the id - pictureUrl/articleId
        Long noteId = getNoteLatestId();
        //set picture - content/articleId
        NotePicture notePicture = new NotePicture();
        notePicture.setPictureUrl(noteDto.getPictureUrl());
        notePicture.setNoteId(noteId);
        notePictureMapper.insertSelective(notePicture);
        //set content
        NoteContent noteContent = new NoteContent();
        noteContent.setContent(noteDto.getContent());
        noteContent.setNoteId(noteId);
        noteContentMapper.insertSelective(noteContent);
    }

    //delete a note
    @Override
    public void deleteNoteById(Long id) {
        // get note information
        NoteDto noteDto = getOneById(id);
        noteInfoMapper.deleteByPrimaryKey(noteDto.getId());
        noteContentMapper.deleteByPrimaryKey(noteDto.getNoteContentId());
        notePictureMapper.deleteByPrimaryKey(noteDto.getNotePictureId());
    }

    @Override
    public void updateNote(NoteDto noteDto) {
        //update noteInfo
        NoteInfo noteInfo = new NoteInfo();
        noteInfo.setId(noteDto.getId());
        noteInfo.setTitle(noteDto.getTitle());
        noteInfo.setIsTop(noteDto.isTop());
        noteInfo.setTraffic(noteDto.getTraffic());
        noteInfoMapper.updateByPrimaryKeySelective(noteInfo);

        //update picture
        NotePictureExample pictureExample = new NotePictureExample();
        pictureExample.or().andNoteIdEqualTo(noteDto.getId());
        NotePicture notePicture = notePictureMapper.selectByExample(pictureExample).get(0);
        notePicture.setPictureUrl(notePicture.getPictureUrl());
        notePictureMapper.updateByPrimaryKeySelective(notePicture);

        //update content
        NoteContentExample noteContentExample = new NoteContentExample();
        noteContentExample.or().andNoteIdEqualTo(noteDto.getId());
        NoteContent noteContent = noteContentMapper.selectByExample(noteContentExample).get(0);
        noteContent.setContent(noteDto.getContent());
        noteContentMapper.updateByPrimaryKeySelective(noteContent);
    }

    // get a note
    @Override
    public NoteDto getOneById(Long id) {
        //note information
        NoteDto noteDto = new NoteDto();
        NoteInfo noteInfo = noteInfoMapper.selectByPrimaryKey(id);
        noteDto.setId(noteInfo.getId());
        noteDto.setTitle(noteInfo.getTitle());
        noteDto.setTop(noteInfo.getIsTop());
        noteDto.setCreateBy(noteInfo.getCreateBy());

        // increase the number of view
        noteInfo.setTraffic(noteInfo.getTraffic() + 1);
        noteDto.setTraffic(noteInfo.getTraffic() + 1);
        noteInfoMapper.updateByPrimaryKey(noteInfo);

        // content
//       NoteContentExample noteContentExample = new NoteContentExample();
//       noteContentExample.or().andNoteIdEqualTo(id);
//       System.out.println(noteContentExample.or().andNoteIdEqualTo(id));
       NoteContent noteContent = noteContentMapper.selectByPrimaryKey(id);
       noteDto.setNoteContentId(noteContent.getId());
       noteDto.setContent(noteContent.getContent());

        // picture
        NotePictureExample notePictureExample = new NotePictureExample();
        notePictureExample.or().andNoteIdEqualTo(id);
        NotePicture notePicture = notePictureMapper.selectByExample(notePictureExample).get(0);
        noteDto.setPictureUrl(notePicture.getPictureUrl());
        noteDto.setNotePictureId(notePicture.getId());
        return noteDto;
    }

    @Override
    public NotePicture getPictureByNoteId(Long id) {
        NotePictureExample example = new NotePictureExample();
        example.or().andNoteIdEqualTo(id);
        return notePictureMapper.selectByExample(example).get(0);
    }

    @Override
    public List<NoteWithPictureDto> listAll() {
        List<NoteWithPictureDto> notes = listAllNotesWithPicture();
        LinkedList<NoteWithPictureDto> list = new LinkedList<>();
        for(NoteWithPictureDto note: notes){
            if(note.getTop()){
                list.addFirst(note);
            }else{
                list.addLast(note);
            }
        }
        notes = new ArrayList<>(list);
        return notes;
    }

    @Override
    public List<NoteWithPictureDto> listLatest() {
       List<NoteWithPictureDto> notes = listAllNotesWithPicture();
       if(notes.size() >= MAX_LASTEST_NOTE_COUNT){
           List<NoteWithPictureDto> tempNotes = new ArrayList<>();
           for(int i = 0; i < 1; i++){
                  tempNotes.add(notes.get(i));
           }
           return tempNotes;
       }
        return notes;
    }

    @Override
    public List<NoteDto> listAllNotes() {
        List<NoteDto> notes = getAllNotes();
        LinkedList<NoteDto> list = new LinkedList<>();
        for(NoteDto note: notes){
            if(note.isTop()){
                list.addFirst(note);
            }else{
                list.addLast(note);
            }
        }
        notes = new ArrayList<>(list);
        return notes;
    }

    private Long getNoteLatestId(){
        NoteInfoExample example = new NoteInfoExample();
        example.setOrderByClause("id desc");
        return noteInfoMapper.selectByExample(example).get(0).getId();
    }

    private List<NoteWithPictureDto> listAllNotesWithPicture(){
        NoteInfoExample example = new NoteInfoExample();
        example.setOrderByClause("id desc");
        //return all
        List<NoteInfo> noteInfos = noteInfoMapper.selectByExample(example);
        List<NoteWithPictureDto> notes = new ArrayList<>();
        for(NoteInfo noteInfo : noteInfos){
            NoteWithPictureDto noteWithPictureDto = new NoteWithPictureDto();

            //basic information
            noteWithPictureDto.setId(noteInfo.getId());
            noteWithPictureDto.setTitle(noteInfo.getTitle());
            noteWithPictureDto.setTop(noteInfo.getIsTop());
            noteWithPictureDto.setTraffic(noteInfo.getTraffic());
            
            //picture information
            NotePictureExample notePictureExample = new NotePictureExample();
            notePictureExample.or().andNoteIdEqualTo(noteInfo.getId());
            NotePicture notePicture = notePictureMapper.selectByExample(notePictureExample).get(0);
            noteWithPictureDto.setNotePictureId(notePicture.getId());
            noteWithPictureDto.setPictureUrl(notePicture.getPictureUrl());

            notes.add(noteWithPictureDto);
        }
        return notes;
    }

    private List<NoteDto> getAllNotes(){
        NoteInfoExample example = new NoteInfoExample();
        example.setOrderByClause("id desc");
        //return all
        List<NoteInfo> noteInfos = noteInfoMapper.selectByExample(example);
        List<NoteDto> notes = new ArrayList<>();
        for(NoteInfo noteInfo : noteInfos){
            NoteDto noteDto = new NoteDto();

            //basic information
            noteDto.setId(noteInfo.getId());
            noteDto.setTitle(noteInfo.getTitle());
            noteDto.setTop(noteInfo.getIsTop());
            noteDto.setTraffic(noteInfo.getTraffic());
            noteDto.setCreateBy(noteInfo.getCreateBy());

            //picture
            NotePictureExample notePictureExample = new NotePictureExample();
            notePictureExample.or().andNoteIdEqualTo(noteInfo.getId());
            NotePicture notePicture = notePictureMapper.selectByExample(notePictureExample).get(0);
            noteDto.setNotePictureId(notePicture.getId());
            noteDto.setPictureUrl(notePicture.getPictureUrl());

            //content
            NoteContent noteContent = noteContentMapper.selectByPrimaryKey(noteInfo.getId());
            noteDto.setNoteContentId(noteContent.getId());
            noteDto.setContent(Markdown2HtmlUtil.markdown2html(noteContent.getContent()));

            notes.add(noteDto);

        }
        return notes;
    }
}
