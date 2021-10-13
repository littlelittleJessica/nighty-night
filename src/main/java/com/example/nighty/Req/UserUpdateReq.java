package com.example.nighty.Req;

import reactor.util.annotation.Nullable;

import javax.validation.constraints.Null;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/8
 */
public class UserUpdateReq {

    private Long id;

    private String username;

    private String email;

    private String photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
