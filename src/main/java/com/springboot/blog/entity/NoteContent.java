package com.springboot.blog.entity;

import java.util.Date;

public class NoteContent {
    private Long id;

    private Long noteId;

    private Date createBy;

    private Date modifiedBy;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public Date getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    public Date getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Date modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "NoteContent{" +
                "id=" + id +
                ", noteId=" + noteId +
                ", createBy=" + createBy +
                ", modifiedBy=" + modifiedBy +
                ", content='" + content + '\'' +
                '}';
    }
}