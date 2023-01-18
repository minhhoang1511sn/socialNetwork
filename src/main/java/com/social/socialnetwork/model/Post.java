package com.social.socialnetwork.model;

import java.util.List;

public class Post {
    private Long id;
    private User user;
    private Long countLike;
    private List<Comment> commentList;
    private List<Image> imageList;
    private List<Video> videoList;
    private String content;

    public Post(Long id, User user, Long countLike, List<Comment> commentList, List<Image> imageList, List<Video> videoList, String content) {
        this.id = id;
        this.user = user;
        this.countLike = countLike;
        this.commentList = commentList;
        this.imageList = imageList;
        this.videoList = videoList;
        this.content = content;
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

    public Long getCountLike() {
        return countLike;
    }

    public void setCountLike(Long countLike) {
        this.countLike = countLike;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
