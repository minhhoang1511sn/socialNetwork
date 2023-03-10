package com.social.socialnetwork.Service.Iplm;

import com.social.socialnetwork.Service.Cloudinary.CloudinaryUpload;
import com.social.socialnetwork.Service.CommentService;
import com.social.socialnetwork.dto.CommentReq;
import com.social.socialnetwork.dto.PostResp;
import com.social.socialnetwork.exception.AppException;
import com.social.socialnetwork.model.Comment;
import com.social.socialnetwork.model.Image;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private final CloudinaryUpload cloudinaryUpload;

    @Override
    public Comment findById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.orElse(null);
    }
    @Override
    public String upImage(Long commentID,MultipartFile file) throws IOException {
        Long id = Utils.getIdCurrentUser();
        User user = userRepository.findUserById(id);
        Comment comment = findById(commentID);
        if (user == null || comment==null )
            throw new AppException(404, "User ID not found");
        Image imgUrl = new Image();
        if (comment.getImage() != null && comment.getImage().getImgLink().startsWith("https://res.cloudinary.com/minhhoang1511/image/upload")) {
            imgUrl = comment.getImage();
            imgUrl.setImgLink(cloudinaryUpload.uploadImage(file,imgUrl.getImgLink()));
        }else
            imgUrl.setImgLink(cloudinaryUpload.uploadImage(file,null));
        imgUrl.setUser(user);
        comment.setImage(imgUrl);
        commentRepository.save(comment);
        return imgUrl.getImgLink();
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
    public List<Comment> getAllCommentByPost(Long postid) {
        Post post = postRepository.getReferenceById(postid);
        List<Comment> commentList = commentRepository.getCommentByPost(post);
        return commentList;
    }

    @Override
    public Comment updateComment(CommentReq commentReq) {
        Long userId = Utils.getIdCurrentUser();
        boolean check = userRepository.existsById(userId);
        if(check){
            Comment commentUpdate = findById(commentReq.getId());
            if(commentUpdate.getUser().getId() == userId)
            {
                if(commentUpdate != null) {
                    commentUpdate.setContent(commentReq.getContent());
                    commentUpdate.setNumReply(commentReq.getNumReply());
                    return commentUpdate;
                } else throw new AppException(404, "Comment ID not found");
            }
            else  throw new AppException(500, "User don't have permission");
        }
        else throw new AppException(500, "User not found");
    }

    @Override
    public boolean deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        Long currentUser = Utils.getIdCurrentUser();
        if(currentUser == comment.getUser().getId())
        {
            if(comment != null){
                commentRepository.deleteById(id);
                Post post = comment.getPost();
                List<Comment> commentList = post.getCommentList();
                commentList.remove(comment);
                post.setCommentList(commentList);
                return  true;
            }
            else{
                throw new AppException(404, "Comment ID not found");
            }
        }
        else{
            throw new AppException(500, "User don't have permission to delete this comment");
        }
    }

    @Override
    public double updateRate(Comment comment) {
        return 0;
    }
}
