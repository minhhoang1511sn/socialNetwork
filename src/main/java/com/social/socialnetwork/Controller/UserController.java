package com.social.socialnetwork.Controller;

import com.social.socialnetwork.Service.UserService;
import com.social.socialnetwork.dto.PasswordDTO;
import com.social.socialnetwork.dto.ResponseDTO;
import com.social.socialnetwork.dto.UserReq;
import com.social.socialnetwork.exception.AppException;
import com.social.socialnetwork.model.User;
import com.social.socialnetwork.repository.UserRepository;
import freemarker.template.TemplateException;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser(){
        User user = userService.getCurrentUser();
        return ResponseEntity.ok().body(user);
    }
    @GetMapping("/alluser")
    public ResponseEntity<?> getAllUser(){
        List<User> userlist = userService.findAllUser();
        return ResponseEntity.ok().body(userlist);
    }
    @GetMapping("/search-user")
    public ResponseEntity<?> getUserByName(@RequestParam("query") String query){

        List<User> user = userService.findUserByUserName(query);
        return ResponseEntity.ok().body(user);
    }
    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody UserReq userReq){
        User usersUpdate = userService.updateUser(userReq);
        if (usersUpdate!= null){
            return ResponseEntity.ok(new ResponseDTO(true,"Success",usersUpdate));
        }else
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false,"User ID not exits",null));

    }
    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordDTO passwordDTO){
        User user = userService.findUserByEmail(passwordDTO.getEmail());
        if(!userService.checkIfValidOldPassword(user,passwordDTO.getOldPassword())) {
            return ResponseEntity.ok().body(new ResponseDTO(false,"Invalid Old Password",
                    null));
        }
        //Save New Password
        userService.changePassword(user, passwordDTO.getNewPassword());
        return ResponseEntity.ok().body(new ResponseDTO(true,"Password Changed Successfully",
                null));
    }
        @DeleteMapping("/admin/delete-user/{id}")
        public ResponseEntity<?> DeleteUser(@PathVariable Long id){
            boolean check = userService.deleteUser(id);
            if(check)
            return  ResponseEntity.ok().body(new ResponseDTO(true,"User has been deleted success",
                    null));
            else
                return  ResponseEntity.ok().body(new ResponseDTO(false,"User cannot deleted",
                        null));
        }

    @PutMapping(value = "/user/avatar",consumes = {
            "multipart/form-data"})
    public ResponseEntity<?> upAvatar(@Parameter(
                description = "Files to be uploaded",
            content =  @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    )
                                      @RequestParam(value = "image", required =
                                              false) MultipartFile file) throws IOException {
        String url = userService.upAvartar(file);

        return ResponseEntity.ok().body(new ResponseDTO(true,"Success",
                url));
    }

}
