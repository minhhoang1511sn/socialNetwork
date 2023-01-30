package com.social.socialnetwork.Service;

import com.social.socialnetwork.config.JwtService;
import com.social.socialnetwork.dto.AuthenticationReqest;
import com.social.socialnetwork.dto.AuthenticationResponse;
import com.social.socialnetwork.dto.RegisterReqest;
import com.social.socialnetwork.exception.AppException;
import com.social.socialnetwork.model.ConfirmationCode;
import com.social.socialnetwork.model.Role;
import com.social.socialnetwork.repository.ConfirmationCodeRepository;
import com.social.socialnetwork.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.GenericValidator;

import com.social.socialnetwork.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ConfirmationCodeRepository confirmationCodeRepository;
    public User register(RegisterReqest request) {
        if (!GenericValidator.isEmail(request.getEmail()))
            throw new AppException(400, "Wrong email");
        boolean check = userRepository.existsByEmail(request.getEmail());
        if (check) {
            throw new AppException(400,"Email already exits");
        }

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .Enabled(request.getEnabled())
                .build();
        return userRepository.save(user);

    }

    public AuthenticationResponse authenticate(AuthenticationReqest reqest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        reqest.getEmail(),
                        reqest.getPassword()
                )
        );
        var user = userRepository.findByEmail(reqest.getEmail())
                .orElseThrow();
        if(!user.getEnabled())
        {
           return null;
        }
        else {
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken).build();
        }
    }

    public void saveVerificationTokenForUser(User users, String token) {
        ConfirmationCode verificationToken = new ConfirmationCode(users, token);
        System.out.println(verificationToken.getExpirationTime());
        confirmationCodeRepository.save(verificationToken);
    }
    public AuthenticationResponse validateVerificationCode(String code, String email) {
        ConfirmationCode verificationCode
                = confirmationCodeRepository.findVerificationCodeByCodeAndUser_Email(code, email);

        if (verificationCode == null) {
            return null;
        }

        User user = verificationCode.getUser();

        verificationCode.setToken(null);
        confirmationCodeRepository.save(verificationCode);
        user.setEnabled(true);
        user.setRole(Role.USER);
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();

    }

}
