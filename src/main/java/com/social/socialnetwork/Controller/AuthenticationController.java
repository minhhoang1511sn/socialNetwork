package com.social.socialnetwork.Controller;

import com.social.socialnetwork.Service.AuthenticationService;
import com.social.socialnetwork.Service.EmailService;
import com.social.socialnetwork.dto.*;
import com.social.socialnetwork.exception.AppException;
import com.social.socialnetwork.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private String OTP;
    @PostMapping("/sendingEmail")
    public MailRespone sendEmail(@RequestBody MailRequest request){
        SecureRandom rand = new SecureRandom();
        int _otp = rand.nextInt(1000000);
        OTP = String.valueOf(_otp);
        Map<String,Object> model = new HashMap<>();
        model.put("fullname",request.getFirstName() + " " + request.getLastName());
        model.put("otp", OTP);
        return emailService.sendEmail(request,model);
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterReqest request
    )
    {
        return ResponseEntity.ok(authenticationService.register(request));
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
    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}
