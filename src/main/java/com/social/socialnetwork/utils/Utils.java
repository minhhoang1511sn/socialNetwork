package com.social.socialnetwork.utils;

import com.social.socialnetwork.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
public class Utils {


    public static Long getIdCurrentUser(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        User u = (User) authentication.getPrincipal();
        return u.getId();
    }


}
