package com.social.socialnetwork.model;

public class Video {
    private Long id;
    private String linkVideo;

    public Video(Long id, String linkVideo) {
        this.id = id;
        this.linkVideo = linkVideo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }
}
