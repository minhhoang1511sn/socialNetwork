package com.social.socialnetwork.dto;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserReq {
    private Long id;
    private String email;
    private String address;
    private String gender;
    private String password;
    private String lastName;

}
