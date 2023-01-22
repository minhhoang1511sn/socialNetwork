package com.social.socialnetwork.Service;

import com.social.socialnetwork.dto.LoginRequest;
import com.social.socialnetwork.dto.UserReq;
import com.social.socialnetwork.dto.UserResp;
import com.social.socialnetwork.exception.AppException;
import com.social.socialnetwork.model.User;
import com.social.socialnetwork.model.VerificationToken;
import com.social.socialnetwork.repository.UserRepository;
import com.social.socialnetwork.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.Map;
@Service
@RequiredArgsConstructor

public class UserService {
    @Autowired
    private final UserRepository userRepository;
    public User getCurrentUser() {
        Long id = Utils.getIdCurrentUser();
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(404, "Not found"));
        return user;

    }
}
