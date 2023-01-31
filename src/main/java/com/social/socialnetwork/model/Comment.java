package com.social.socialnetwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private String content;
    @OneToOne(cascade = {CascadeType.ALL})
    private Image imgLink;
    @OneToOne(cascade = {CascadeType.ALL})
    private Video videoLink;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;



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

    public Image getImgLink() {
        return imgLink;
    }

    public void setImgLink(Image imgLink) {
        this.imgLink = imgLink;
    }

    public Video getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(Video videoLink) {
        this.videoLink = videoLink;
    }
}
