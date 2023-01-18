package com.social.socialnetwork.model;

public class PostType {
    private Long id;
    private String type;
    private User user;
    private Post post;

    public PostType(Long id, String type, User user, Post post) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
