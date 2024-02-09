package com.pro.ukart.Dtos;

import com.pro.ukart.Entities.User;

public class UserLoginDto {

    private Long id;
    private User user;

    private String jwt;
    public UserLoginDto() {
        super();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public UserLoginDto(Long id, User user, String jwt) {
        this.id = id;
        this.user = user;
        this.jwt = jwt;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
