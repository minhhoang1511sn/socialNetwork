package com.social.socialnetwork.dto;

import lombok.Data;

@Data
public class UpdateUserReq {
    private String gender;
    private String firstName;
    private String lastName;
    private String address;

}
