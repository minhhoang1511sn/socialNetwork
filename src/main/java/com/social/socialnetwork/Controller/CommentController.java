package com.social.socialnetwork.Controller;

import com.social.socialnetwork.Service.CommentService;
import com.social.socialnetwork.dto.CommentReq;
import com.social.socialnetwork.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/comment")
    public ResponseEntity<?> postComment(@RequestBody CommentReq commentReq){
        try {
            return ResponseEntity.ok(new ResponseDTO(true, "Success", commentService.postComment(commentReq)));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO(false, e.getMessage(), null));
        }
    }
}
