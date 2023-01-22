package com.social.socialnetwork.Service;

import com.social.socialnetwork.Service.Cloudinary.CloudinaryUpload;
import com.social.socialnetwork.dto.UpdateUserReq;
import com.social.socialnetwork.dto.UserResp;
import com.social.socialnetwork.exception.AppException;
import com.social.socialnetwork.model.User;
import com.social.socialnetwork.repository.UserRepository;
import com.social.socialnetwork.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryUpload cloudinaryUpload;

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User updateUser(UpdateUserReq userReq){
        User userUpdate = findById(Utils.getIdCurrentUser());
        if (userUpdate != null) {
            if (userReq.getFirstName()!=null && !userReq.getFirstName().trim().equals(""))
                userUpdate.setFirstName(userReq.getFirstName().trim().replaceAll("  "," "));
            if (userReq.getLastName()!=null && !userReq.getLastName().trim().equals(""))
                userUpdate.setLastName(userReq.getLastName().trim().replaceAll("  "," "));
            if (userReq.getGender()!=null && !userReq.getGender().trim().equals(""))
                userUpdate.setGender(userReq.getGender().trim().replaceAll("  "," "));
            if (userReq.getGender()!=null && !userReq.getGender().trim().equals(""))
                userUpdate.setGender(userReq.getGender().trim().replaceAll("  "," "));
            if (userReq.getAddress()!=null && userReq.getAddress().trim().equals("")) {
                userUpdate.setAddress(userReq.getAddress().trim().replaceAll("  "," "));
            }
            userRepository.save(userUpdate);
            return userUpdate;
        } else return null;

    }
    public String upAvartar(MultipartFile file) throws IOException {
        Long id = Utils.getIdCurrentUser();
        User user = findById(id);
        if (user == null)
            throw new AppException(404, "User ID not found");
        String imgUrl = null;
        if (user.getAvatarLink() != null && user.getAvatarLink().startsWith("https://res.cloudinary.com/minhhoang1511/image/upload")) {
            imgUrl = user.getAvatarLink();
            imgUrl = cloudinaryUpload.uploadImage(file,imgUrl);
        }else
            imgUrl = cloudinaryUpload.uploadImage(file,null);
        user.setAvatarLink(imgUrl);
        userRepository.save(user);
        return imgUrl;
    }

    public User getCurrentUser() {
        Long id = Utils.getIdCurrentUser();
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(404, "Not found"));
        return user;
    }
}
