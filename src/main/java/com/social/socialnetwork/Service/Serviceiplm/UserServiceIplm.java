package com.social.socialnetwork.Service.Serviceiplm;

import com.social.socialnetwork.Service.UserService;
import com.social.socialnetwork.dto.LoginRequest;
import com.social.socialnetwork.dto.UserReq;
import com.social.socialnetwork.dto.UserResp;
import com.social.socialnetwork.model.User;
import com.social.socialnetwork.model.VerificationToken;
import com.social.socialnetwork.repository.UserRepository;
import com.social.socialnetwork.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceIplm implements  UserService {
    private UserRepository userRepository;
    private  ModelMapper modelMapper;

    public UserServiceIplm() {
    }

    @Override
    public User saveAdmin(UserReq userReq) {
        return null;
    }

    @Override
    public User saveRegister(UserReq userReq) {
        return null;
    }

    @Override
    public String validateVerificationToken(String token, String email) {
        return null;
    }

    @Override
    public void saveVerificationTokenForUser(User user, String token) {

    }

    @Override
    public User findById(Long id) {
        return null;
    }
    @Override
    public List<UserResp> findAll(Pageable pageable) {
        List<UserResp> results = new ArrayList<>();
        List<User> users = userRepository.findAll( pageable).getContent();
        for (User item : users) {
            UserResp userResp = modelMapper.map(item, UserResp.class);
            results.add(userResp);
        }
        return results;
    }

    @Override
    public User updateUser(UserReq userReq) {
        User userUpdate = findById(Utils.getIdCurrentUser());
        if (userUpdate != null) {
            if (userReq.getLastName()!=null && !userReq.getLastName().trim().equals(""))
                userUpdate.setLastName(userReq.getLastName().trim().replaceAll("  "," "));
            if (userReq.getGender()!=null && !userReq.getGender().trim().equals(""))
                userUpdate.setGender(userReq.getGender().trim().replaceAll("  "," "));
            if (userReq.getAddress()!=null && userReq.getAddress().trim().equals("")) {
                userUpdate.setAddress(userReq.getAddress().trim().replaceAll("  "," "));
                };
        userRepository.save(userUpdate);
            return userUpdate;
        } else return null;
    }

    @Override
    public VerificationToken SendToken(String email) {
        return null;
    }

    @Override
    public VerificationToken GetNewOTP(Long uid) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public User validatePasswordResetToken(String token, String email) {
        return null;
    }

    @Override
    public void changePassword(User user, String newPassword) {

    }

    @Override
    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return false;
    }

    @Override
    public String upAvartar(MultipartFile file) throws IOException {
        return null;
    }

    @Override
    public Map<String, String> login(LoginRequest loginRequest, HttpServletRequest request) {
        return null;
    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public void disableUserById(Long usersId) {

    }
}
