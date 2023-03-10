package com.social.socialnetwork.Service.Iplm;

import com.social.socialnetwork.Service.Cloudinary.CloudinaryUpload;
import com.social.socialnetwork.Service.PostService;
import com.social.socialnetwork.dto.PostReq;
import com.social.socialnetwork.exception.AppException;
import com.social.socialnetwork.model.*;
import com.social.socialnetwork.repository.*;
import com.social.socialnetwork.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostServiceIplm implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CloudinaryUpload cloudinaryUpload;
    private final ImageRepository imageRepository;
    private final VideoRepository videoRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    @Override
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
            post.setCreateDate(new Date());
            return postRepository.save(post);
        } else {
            throw new AppException(404, "Product or Comment not exits.");
        }
    }

    @Override
    public Post findById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElse(null);

    }
    @Override
    public boolean deletePost(Long id){
        Long idCurrentUser = Utils.getIdCurrentUser();
        Post postDelete = postRepository.findById(id).orElse(null);
        if(postDelete !=null && postDelete.getUser().getId() == idCurrentUser)
        {
            List<Image> imageList = postDelete.getImagesList();
            List<Video> videoList = postDelete.getVideosList();
            List<Comment> commentList = postDelete.getCommentList();

            for (Image image : imageList) {
                imageRepository.deleteById(image.getId());
            }
            for (Video video : videoList) {
                videoRepository.deleteById(video.getId());
            }
            for (Comment comment : commentList) {
                commentRepository.deleteById(comment.getId());
            }
            postDelete.setPostType(null);
            postDelete.setImagesList(null);
            postDelete.setVideosList(null);
            postDelete.setCommentList(null);
            postDelete.setUser(null);
            postRepository.deleteById(id);
            return true;
        }
        else throw new AppException(404, "Post does not exits.");
    }
    @Override
    public List<Post> getAllPost()
    {
        Long idCurrentUser = Utils.getIdCurrentUser();
        User user = userRepository.findUserById(idCurrentUser);
        List<Post> posts = postRepository.findPostByUser(user);
        return posts;
    }

    @Override
    public Post updatePost(PostReq postReq) {
        Post postUpdate = findById(postReq.getId());
        Long idCurrentUser = Utils.getIdCurrentUser();
        if(postUpdate != null && idCurrentUser == postUpdate.getUser().getId())
        {
            Post post = modelMapper.map(postReq,Post.class);
            List<Image> imageList = postUpdate.getImagesList();
            List<Video> videoList = postUpdate.getVideosList();
            List<Comment> commentList = postUpdate.getCommentList();
            PostType postType = postUpdate.getPostType();
            post.setCommentList(commentList);
            post.setImagesList(imageList);
            post.setVideosList(videoList);
            post.setCreateDate(new Date());
            post.setPostType(postType);
            return postRepository.save(post);
        }
        else
            throw new AppException(400,"Post does not exists");
    }
    @Override
    public List<String> uploadListofImage(Long postId, List<MultipartFile> images) {
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
    @Override
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
