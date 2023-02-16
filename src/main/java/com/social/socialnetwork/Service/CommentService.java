package com.social.socialnetwork.Service;

import com.social.socialnetwork.dto.CommentReq;
import com.social.socialnetwork.dto.CommentResp;
import com.social.socialnetwork.model.Comment;
import com.social.socialnetwork.model.Post;

import java.util.List;

public interface CommentService {
    Comment findById(Long id);
    Comment postComment(CommentReq commentReq);
    List<Comment> getAllCommentByPost(Long postid);
     Comment updateComment(CommentReq commentReq);
    boolean deleteComment(Long id);
    double updateRate(Comment comment);
}
