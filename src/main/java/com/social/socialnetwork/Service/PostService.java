package com.social.socialnetwork.Service;

import com.social.socialnetwork.dto.PostReq;
import com.social.socialnetwork.model.Post;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;



public interface PostService {
     Post createPost(PostReq postReq);
     Post findById(Long id);
     boolean deletePost(Long id);
     List<Post> getAllPost();
     Post updatePost(PostReq postReq);
     List<String> uploadListofImage(Long postId, List<MultipartFile> images) ;
     List<String> uploadListofVideo(Long postId, List<MultipartFile> VideoReqs);
}
