package com.social.socialnetwork.model;

public class Message {
    private Long id;
    private User user;
    private String text;
    private String imgLink;
    private String videoLink;

    public Message(Long id, User user, String text, String imgLink, String videoLink) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.imgLink = imgLink;
        this.videoLink = videoLink;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }
}
