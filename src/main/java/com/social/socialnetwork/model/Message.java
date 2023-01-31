package com.social.socialnetwork.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private String text;
    @ManyToOne(fetch = FetchType.EAGER)
    private Image imgLink;
    @ManyToOne(fetch = FetchType.EAGER)
    private Video videoLink;

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
