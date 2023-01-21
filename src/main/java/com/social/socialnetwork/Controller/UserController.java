package com.social.socialnetwork.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/user")
    public String user1(){
        return "User";
    }
    @GetMapping("/admin")
    public String admin(){
        return "Admin";
    }
}
