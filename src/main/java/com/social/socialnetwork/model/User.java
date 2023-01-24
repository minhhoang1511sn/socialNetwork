package com.social.socialnetwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String password;

    @OneToOne(cascade = {CascadeType.ALL})
    private Image avatarLink;
    private Date  birthday;
    private String gender;
    @OneToMany(mappedBy = "id",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<User> friendList;
    @OneToMany(mappedBy = "id",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<User> friendReqests;
    @OneToMany(mappedBy = "id",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<User> fridendRespond;
    @OneToMany(mappedBy = "id",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<User> suggestions;
    @Enumerated(EnumType.STRING)
    private Role role;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Image getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(Image avatarLink) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
