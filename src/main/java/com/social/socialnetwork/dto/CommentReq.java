package com.social.socialnetwork.dto;

import lombok.Data;

@Data
public class CommentReq {
    private Long id;
    private String content;
    private Double rate;
    private Long postId;
    private Long parentCommentId;
    private Long userId;
    private Long numReply;
}
