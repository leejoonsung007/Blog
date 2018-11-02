package com.springboot.blog.dto;

public class NoteWithPictureDto {

    //tbl_note_info
    private Long id;
    private String title;
    private Boolean isTop;
    private Integer traffic;

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

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public Integer getTraffic() {
        return traffic;
    }

    public void setTraffic(Integer traffic) {
        this.traffic = traffic;
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
}
