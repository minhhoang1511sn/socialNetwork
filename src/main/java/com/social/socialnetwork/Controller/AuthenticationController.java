package com.social.socialnetwork.Controller;

import com.social.socialnetwork.Service.AuthenticationService;
import com.social.socialnetwork.Service.EmailService;
import com.social.socialnetwork.Service.UserService;
import com.social.socialnetwork.dto.*;
import com.social.socialnetwork.model.User;
import com.social.socialnetwork.repository.UserRepository;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final UserService userService;
    final String TITLE_SUBJECT_EMAIL = "Nhom 2 Register TOKEN";
    final String RESET_PASSWORD_TOKEN = "Reset Password Token";

    @PostMapping("/register-email")
    public ResponseEntity<?> register(
            @RequestBody RegisterReqest request
    ) throws TemplateException, MessagingException, IOException {
         authenticationService.register(request);
        User users = userRepository.findUserByEmail(request.getEmail());
        String token = RandomStringUtils.randomAlphanumeric(6).toUpperCase();
        authenticationService.saveVerificationTokenForUser(users,token);

        Map<String,Object> model = new HashMap<>();
        model.put("token",token);
        model.put("title", TITLE_SUBJECT_EMAIL);
        model.put("subject", TITLE_SUBJECT_EMAIL);
        emailService.sendEmail(request.getEmail(), model);

        return ResponseEntity.ok(new ResponseDTO(true,"Sending email",
                null));
    }
    @RequestMapping(value = "/verifyRegistration/{email}/{code}", method = RequestMethod.GET)
    public ResponseEntity<?> verifyRegistration(@PathVariable(value = "code") String code,
                                                @PathVariable(value = "email") String email) {
        AuthenticationResponse result = authenticationService.validateVerificationCode(code,email);
        if(result==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(result);
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
