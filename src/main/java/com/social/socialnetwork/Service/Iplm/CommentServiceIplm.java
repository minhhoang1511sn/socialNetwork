package com.social.socialnetwork.Service.Iplm;

import com.social.socialnetwork.Service.CommentService;
import com.social.socialnetwork.dto.CommentReq;
import com.social.socialnetwork.dto.PostResp;
import com.social.socialnetwork.exception.AppException;
import com.social.socialnetwork.model.Comment;
import com.social.socialnetwork.model.Post;
import com.social.socialnetwork.model.User;
import com.social.socialnetwork.repository.CommentRepository;
import com.social.socialnetwork.repository.PostRepository;
import com.social.socialnetwork.repository.UserRepository;
import com.social.socialnetwork.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceIplm implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    @Override
    public Comment findById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.orElse(null);
    }

    @Override
    public Comment postComment(CommentReq commentReq) {
        Long userId = Utils.getIdCurrentUser();
        boolean check = userRepository.existsById(userId) && postRepository.existsById(commentReq.getPostId());
        if(check){
                Comment comment = modelMapper.map(commentReq, Comment.class);
                User user = userRepository.getReferenceById(userId);
                Post post = postRepository.findById(commentReq.getPostId()).orElse(null);
                comment.setUser(user);
                comment.setPost(post);
                comment.setNumReply(0L);
                comment.setCreateTime(new Date());
                return commentRepository.save(comment);
        } else {
            throw new AppException(404, "Post or Comment not exits.");
        }
    }

    @Override
    public Comment editComment(CommentReq commentReq) {
        return null;
    }

    @Override
    public List<Comment> getAllCommentByPost(Post post) {
        return null;
    }

    @Override
    public Comment updateComment(CommentReq commentReq) {
        return null;
    }

    @Override
    public boolean deleteComment(Long id) {
        return false;
    }

    @Override
    public double updateRate(Comment comment) {
        return 0;
    }
}
