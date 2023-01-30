package com.social.socialnetwork.Service;

import com.social.socialnetwork.Service.Cloudinary.CloudinaryUpload;
import com.social.socialnetwork.dto.PostReq;
import com.social.socialnetwork.exception.AppException;
import com.social.socialnetwork.model.Image;
import com.social.socialnetwork.model.Post;
import com.social.socialnetwork.model.User;
import com.social.socialnetwork.model.Video;
import com.social.socialnetwork.repository.ImageRepository;
import com.social.socialnetwork.repository.PostRepository;
import com.social.socialnetwork.repository.UserRepository;
import com.social.socialnetwork.repository.VideoRepository;
import com.social.socialnetwork.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CloudinaryUpload cloudinaryUpload;
    private final ImageRepository imageRepository;
    private final VideoRepository videoRepository;
    public Post createPost(PostReq postReq)
    {
        Long idCurrentUser = Utils.getIdCurrentUser();
        boolean check = userRepository.existsById(idCurrentUser);
        if(check){
                Post post = new Post();
                User user = userRepository.getReferenceById(idCurrentUser);
                post.setContent(postReq.getContent());
                post.setCountLike(postReq.getCountLike());
                post.setPostType(postReq.getPostType());
                post.setUser(user);
                return postRepository.save(post);
        } else {
            throw new AppException(404, "Product or Comment not exits.");
        }
    }
    public List<Post> getAllPost()
    {
        Long idCurrentUser = Utils.getIdCurrentUser();
        User user = userRepository.findUserById(idCurrentUser);
        List<Post> posts = postRepository.findPostByUser(user);
        return posts;
    }

    public List<String> uploadListofImage(Long postId, List<MultipartFile> images) throws IOException {
        Long idCurrentUser = Utils.getIdCurrentUser();
        boolean check = userRepository.existsById(idCurrentUser);
        User user = userRepository.findUserById(idCurrentUser);
        List<Image> Images = new ArrayList<>();
        Post post = postRepository.getReferenceById(postId);
        if(check && post!=null){
            images.forEach(img ->{
                try {
                    String url = cloudinaryUpload.uploadImage(img,null);
                    Image imgelement = new Image();
                    imgelement.setImgLink(url);
                    imgelement.setPost(post);
                    imgelement.setUser(user);
                    Images.add(imgelement);
                } catch (IOException e) {
                    throw new AppException(400,"Failed");
                }
            });
            imageRepository.saveAll(Images);
            post.setImagesList(Images);
            List<String> urls = new ArrayList<>();
            Images.forEach(img -> {
                urls.add(img.getImgLink());
            });
            return urls;
        }
        else
            return null;

    }
    public List<String> uploadListofVideo(Long postId, List<MultipartFile> VideoReqs){
        Long idCurrentUser = Utils.getIdCurrentUser();
        boolean check = userRepository.existsById(idCurrentUser);
        User user = userRepository.findUserById(idCurrentUser);
        List<Video> Videos = new ArrayList<>();
        Post post = postRepository.getReferenceById(postId);
        if(check && post!=null){
            VideoReqs.forEach(v ->{
                try {
                    String url = cloudinaryUpload.uploadVideo(v,null);
                    Video vlement = new Video();
                    vlement.setLinkVideo(url);
                    vlement.setPost(post);
                    vlement.setUser(user);
                    Videos.add(vlement);
                } catch (IOException e) {
                    throw new AppException(400,"Failed");
                }
            });
            videoRepository.saveAll(Videos);
            List<String> urls = new ArrayList<>();
            Videos.forEach(v -> {
                urls.add(v.getLinkVideo());
            });
            return urls;
        }
        else
            return null;
    }
}
