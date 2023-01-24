package com.social.socialnetwork.Controller;

import com.social.socialnetwork.Service.AuthenticationService;
import com.social.socialnetwork.dto.AuthenticationReqest;
import com.social.socialnetwork.dto.AuthenticationResponse;
import com.social.socialnetwork.dto.RegisterReqest;
import com.social.socialnetwork.dto.ResponseDTO;
import com.social.socialnetwork.exception.AppException;
import com.social.socialnetwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterReqest request
    )
    {   if(!userRepository.findByEmail(request.getEmail()).isPresent())
        return ResponseEntity.ok(authenticationService.register(request));
        else return  ResponseEntity.ok(new ResponseDTO(false,"Email is Exists",null));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationReqest request
    )
    {
        if(userRepository.findByEmail(request.getEmail()).isPresent())
        return ResponseEntity.ok(authenticationService.authenticate(request));
        else return  ResponseEntity.ok("Email is not Exists");
    }
}
