package com.social.socialnetwork.Service;

import com.social.socialnetwork.dto.LoginRequest;
import com.social.socialnetwork.dto.UserReq;
import com.social.socialnetwork.dto.UserResp;
import com.social.socialnetwork.model.User;
import com.social.socialnetwork.model.VerificationToken;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserService {
    User saveAdmin(UserReq userReq);
    User saveRegister(UserReq userReq);
    String validateVerificationToken(String token,String email);
    void saveVerificationTokenForUser(User user, String token);
    User findById(Long id);
    List<UserResp> findAll(Pageable pageable);
    User updateUser(UserReq userReq);
    VerificationToken SendToken(String email);
    VerificationToken GetNewOTP(Long uid);
    User findUserByEmail(String email);
    User validatePasswordResetToken(String token,String email);

    void changePassword(User user, String newPassword);

    boolean checkIfValidOldPassword(User user, String oldPassword);

    String upAvartar(MultipartFile file) throws IOException;
    Map<String,String> login(LoginRequest loginRequest, HttpServletRequest request);
    User getCurrentUser();

    void disableUserById(Long usersId);
}
