package com.social.socialnetwork.Service;

import com.social.socialnetwork.Service.Cloudinary.CloudinaryUpload;
import com.social.socialnetwork.dto.UserReq;
import com.social.socialnetwork.exception.AppException;
import com.social.socialnetwork.model.Image;
import com.social.socialnetwork.model.User;
import com.social.socialnetwork.repository.ConfirmationCodeRepository;
import com.social.socialnetwork.repository.UserRepository;
import com.social.socialnetwork.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final CloudinaryUpload cloudinaryUpload;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationCodeRepository confirmationCodeRepository;

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }
    public List<User> findAllUser() {
        List<User> users = userRepository.findAll();
        return users;
    }
    public User updateUser(UserReq userReq){
        User userUpdate = findById(Utils.getIdCurrentUser());
        if (userUpdate != null) {
            if (userReq.getFirstName()!=null && !userReq.getFirstName().trim().equals(""))
                userUpdate.setFirstName(userReq.getFirstName().trim().replaceAll("  "," "));
            if (userReq.getLastName()!=null && !userReq.getLastName().trim().equals(""))
                userUpdate.setLastName(userReq.getLastName().trim().replaceAll("  "," "));
            if (userReq.getGender()!=null && !userReq.getGender().trim().equals(""))
                userUpdate.setGender(userReq.getGender().trim().replaceAll("  "," "));
            if (userReq.getAddress()!=null && !userReq.getAddress().trim().equals("")) {
                userUpdate.setAddress(userReq.getAddress());
            }
            if (userReq.getBirthday()!=null) {
                userUpdate.setBirthday(userReq.getBirthday());
            }
            if (userReq.getRole()!=null) {
                userUpdate.setRole(userReq.getRole());
            }
            userRepository.save(userUpdate);
            return userUpdate;
        } else return null;

    }
    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    public String upAvartar(MultipartFile file) throws IOException {
        Long id = Utils.getIdCurrentUser();
        User user = findById(id);
        if (user == null)
            throw new AppException(404, "User ID not found");
        Image imgUrl = new Image();
        if (user.getAvatarLink() != null && user.getAvatarLink().getImgLink().startsWith("https://res.cloudinary.com/minhhoang1511/image/upload")) {
            imgUrl = user.getAvatarLink();
            imgUrl.setImgLink(cloudinaryUpload.uploadImage(file,imgUrl.getImgLink()));
        }else
            imgUrl.setImgLink(cloudinaryUpload.uploadImage(file,null));
        imgUrl.setUser(user);
        user.setAvatarLink(imgUrl);
        userRepository.save(user);
        return imgUrl.getImgLink();
    }

    public User getCurrentUser() {
        Long id = Utils.getIdCurrentUser();
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(404, "Not found"));
        return user;
    }



}
