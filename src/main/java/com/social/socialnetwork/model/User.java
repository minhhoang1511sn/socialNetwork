package com.social.socialnetwork.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String password;
    private String avatarLink;
    private Date  birthday;
    private String gender;
    @OneToMany(mappedBy = "id",fetch = FetchType.LAZY)
    private List<User> friendList;
    @OneToMany(mappedBy = "id",fetch = FetchType.LAZY)
    private List<User> friendReqests;
    @OneToMany(mappedBy = "id",fetch = FetchType.LAZY)
    private List<User> fridendRespond;
    @OneToMany(mappedBy = "id",fetch = FetchType.LAZY)
    private List<User> suggestions;
    private String role;
    private Boolean Enabled;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
