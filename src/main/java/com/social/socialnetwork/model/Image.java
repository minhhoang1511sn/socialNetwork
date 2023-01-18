package com.social.socialnetwork.model;

public class Image {
    private Long id;
    private String imgLink;

    public Image(Long id, String imgLink) {
        this.id = id;
        this.imgLink = imgLink;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }
}
