package com.example.nighty.Resp;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/8
 */
public class UserUpdateResp {
    private Long id;

    private String username;

    private String mobile;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
