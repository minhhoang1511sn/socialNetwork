package com.social.socialnetwork.dto;

import com.social.socialnetwork.model.*;
import lombok.Data;

import java.util.List;

@Data
public class PostReq {
    private Long user;
    private Long countLike;
    private List<Comment> commentList;
    private List<Image> imageList;
    private List<Video> videoList;
    private String content;
    private PostType postType;
}
