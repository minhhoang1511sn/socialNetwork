package com.social.socialnetwork.Service;

import com.social.socialnetwork.dto.PostReq;
import com.social.socialnetwork.exception.AppException;
import com.social.socialnetwork.model.Post;
import com.social.socialnetwork.model.User;
import com.social.socialnetwork.repository.PostRepository;
import com.social.socialnetwork.repository.UserRepository;
import com.social.socialnetwork.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    public Post createPost(PostReq postReq)
    {
        Long idCurrentUser = Utils.getIdCurrentUser();
        boolean check = userRepository.existsById(idCurrentUser);
        if(check){
                Post post = new Post();
                User user = userRepository.getReferenceById(idCurrentUser);
                post.setCommentList(postReq.getCommentList());
                post.setContent(postReq.getContent());
                post.setCountLike(postReq.getCountLike());
                post.setImageList(postReq.getImageList());
                post.setVideoList(postReq.getVideoList());
                post.setPostType(postReq.getPostType());
                post.setUser(user);
                return postRepository.save(post);
        } else {
            throw new AppException(404, "Product or Comment not exits.");
        }
    }
    public Post findById(Long id) {
        Optional<Post> comment = postRepository.findById(id);
        return comment.orElse(null);
    }

   
}
