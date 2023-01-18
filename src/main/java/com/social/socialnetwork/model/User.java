package com.social.socialnetwork.model;

import java.util.Date;
import java.util.List;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String password;
    private String avatarLink;
    private Date  birthday;
    private Boolean sex;
    private List<User> friendList;
    private List<User> friendReqests;
    private List<User> fridendRespond;
    private List<User> suggestions;
    private String role;

    public User(Long id, String firstName, String lastName, String address, String email, String password, String avatarLink, Date birthday, Boolean sex, List<User> friendList, List<User> friendReqests, List<User> fridendRespond, List<User> suggestions, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.password = password;
        this.avatarLink = avatarLink;
        this.birthday = birthday;
        this.sex = sex;
        this.friendList = friendList;
        this.friendReqests = friendReqests;
        this.fridendRespond = fridendRespond;
        this.suggestions = suggestions;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public List<User> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<User> friendList) {
        this.friendList = friendList;
    }

    public List<User> getFriendReqests() {
        return friendReqests;
    }

    public void setFriendReqests(List<User> friendReqests) {
        this.friendReqests = friendReqests;
    }

    public List<User> getFridendRespond() {
        return fridendRespond;
    }

    public void setFridendRespond(List<User> fridendRespond) {
        this.fridendRespond = fridendRespond;
    }

    public List<User> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<User> suggestions) {
        this.suggestions = suggestions;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
