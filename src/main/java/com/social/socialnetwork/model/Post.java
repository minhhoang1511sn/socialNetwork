package com.social.socialnetwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private Long countLike;
    private String content;
    @Enumerated(EnumType.STRING)
    private PostType postType;
    @OneToMany(mappedBy = "id",fetch = FetchType.EAGER)
    private List<Comment> commentList;
    @OneToMany(mappedBy = "id",fetch = FetchType.EAGER)
    private List<Image> imagesList;
    @OneToMany(mappedBy = "id",fetch = FetchType.EAGER)
    private List<Video> videosList;
    public Long getId() {
        return id;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Image> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<Image> imagesList) {
        this.imagesList = imagesList;
    }

    public List<Video> getVideosList() {
        return videosList;
    }

    public void setVideosList(List<Video> videosList) {
        this.videosList = videosList;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
