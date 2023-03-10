package com.social.socialnetwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;
    private Double rate;
    private Long numReply;
    private Date createTime;
    @OneToOne(cascade = {CascadeType.ALL})
    private Image image;
    @JsonIgnore
    @ManyToOne()
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment",cascade = CascadeType.REMOVE)
    private List<Comment> listSubComment;



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
