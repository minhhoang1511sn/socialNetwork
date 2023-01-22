package com.social.socialnetwork.Controller;

import com.social.socialnetwork.Service.UserService;
import com.social.socialnetwork.dto.ResponseDTO;
import com.social.socialnetwork.dto.UpdateUserReq;
import com.social.socialnetwork.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserReq userReq){
        User usersUpdate = userService.updateUser(userReq);
        if (usersUpdate!= null){
            return ResponseEntity.ok(new ResponseDTO(true,"Success",usersUpdate));
        }else
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false,"User ID not exits",null));

    }
}
