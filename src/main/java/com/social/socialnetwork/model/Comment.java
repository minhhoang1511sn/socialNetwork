package com.social.socialnetwork.model;

public class Comment {
    private Long id;
    private User user;
    private String content;
    private String imgLink;
    private String videoLink;

    public Comment(Long id, User user, String content, String imgLink, String videoLink) {
        this.id = id;
        this.user = user;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
