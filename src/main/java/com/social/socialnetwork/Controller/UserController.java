package com.social.socialnetwork.Controller;

import com.social.socialnetwork.Service.UserService;
import com.social.socialnetwork.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/user")
    public ResponseEntity<?> getUser(){
        User user = userService.getCurrentUser();
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/admin")
    public String admin(){
        return "Admin";
    }
}
