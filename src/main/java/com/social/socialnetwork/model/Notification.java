package com.social.socialnetwork.model;

public class Notification {
    private Long id;
    private User user;
    private String content;

    public Notification(Long id, User user, String content) {
        this.id = id;
        this.user = user;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
