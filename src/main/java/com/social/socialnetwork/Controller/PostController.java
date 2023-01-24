package com.social.socialnetwork.Controller;

import com.social.socialnetwork.Service.PostService;
import com.social.socialnetwork.dto.PostReq;
import com.social.socialnetwork.dto.ResponseDTO;
import com.social.socialnetwork.model.Image;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostReq postReq) {
        try {
            return ResponseEntity.ok(new ResponseDTO(true, "Success", postService.createPost(postReq)));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO(false, e.getMessage(), null));
        }

    }

    @PostMapping(value = "/list-of-images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> listOfImages(@RequestParam  Long postId, @RequestParam  List<MultipartFile> images) throws IOException {
        List<String> urls = postService.uploadListofImage(postId,images);
        return ResponseEntity.ok(new ResponseDTO(true,"Success",urls));

    }

    @PostMapping(value = "/list-of-videos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> listOfVideos(@RequestParam  Long postId, @RequestParam  List<MultipartFile> videos) throws IOException {
        List<String> urls = postService.uploadListofVideo(postId,videos);
        return ResponseEntity.ok(new ResponseDTO(true,"Success",urls));

    }
}
