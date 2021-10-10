package com.example.nighty.Req;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/7
 */
public class UserRegisterReq {

    @NotEmpty(message = "Username can not be empty!")
    private String username;

    @NotEmpty(message = "Mobile can not be empty!")
    private String mobile;

    @NotNull(message = "Password can not be empty!")
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
