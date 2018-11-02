package com.springboot.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class NoteDto {

    //tbl_note_info
    private Long id;
    private String title;
    private Date createBy;
    private Integer traffic;
    private boolean isTop;

    //tbl_note_content
    private Long noteContentId;
    private String content;

    //tbl_note_picture
    private Long notePictureId;
    private String pictureUrl;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+1")
    public Date getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    public Integer getTraffic() {
        return traffic;
    }

    public void setTraffic(Integer traffic) {
        this.traffic = traffic;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    public Long getNoteContentId() {
        return noteContentId;
    }

    public void setNoteContentId(Long noteContentId) {
        this.noteContentId = noteContentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getNotePictureId() {
        return notePictureId;
    }

    public void setNotePictureId(Long notePictureId) {
        this.notePictureId = notePictureId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return "NoteDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createBy=" + createBy +
                ", traffic=" + traffic +
                ", isTop=" + isTop +
                ", noteContentId=" + noteContentId +
                ", content='" + content + '\'' +
                ", notePictureId=" + notePictureId +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }
}
