package com.social.socialnetwork.dto;

import com.social.socialnetwork.model.Comment;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentResp {
    private Long id;
    private String content;
    private Date create_time;
    private List<Comment> listSubComment;
    private double rate;
    private UserResp user;
    private PostResp postResp;

}
