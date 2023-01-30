package com.social.socialnetwork.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PasswordDTO {
    @NotBlank(message = "Email may not be blank")
    private String email;
    private String verifyCode;
    private String oldPassword;
    @NotBlank(message = "New Password may not be blank")
    private String newPassword;

}
