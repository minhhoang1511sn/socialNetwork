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

    public String upImage(MultipartFile file) throws IOException {
        Image imgUrl = new Image();
        cloudinaryUpload.uploadImage(file,null);
        return imgUrl.getImgLink();
    }
    public List<String> uploadListofImage(Long postId, List<MultipartFile> images) throws IOException {
        Long idCurrentUser = Utils.getIdCurrentUser();
        boolean check = userRepository.existsById(idCurrentUser);
        List<Image> Images = new ArrayList<>();
        Post post = postRepository.getReferenceById(postId);
        if(check && post!=null){
            images.forEach(img ->{
                try {
                    String url = cloudinaryUpload.uploadImage(img,null);
                    Image imgelement = new Image();
                    imgelement.setImgLink(url);
                    imgelement.setPost(post);
                    Images.add(imgelement);
                } catch (IOException e) {
                    throw new AppException(400,"Failed");
                }
            });
            imageRepository.saveAll(Images);
            List<String> urls = new ArrayList<>();
            Images.forEach(img -> {
                urls.add(img.getImgLink());
            });
            return urls;
        }
        else
            return null;

    }
    public boolean saveNewImage(PostReq postReq, List<Image> ImageReqs) {
        Long idCurrentUser = Utils.getIdCurrentUser();
        boolean check = userRepository.existsById(idCurrentUser);
        Post postcurr = postRepository.getReferenceById(postReq.getId());
        if(check && postcurr!=null) {
            List<Image> Images = new ArrayList<>();
            ImageReqs.forEach(image -> {
                Post post = postRepository.getReferenceById(image.getPost().getId());
                Images.add(new Image(null, image.getImgLink(), post));
            });
            return imageRepository.saveAll(Images).size() > 0;
        }else return false;
    }


    public List<Video> uploadListofVideo(){
        return  null;
    }
}
