package com.example.nighty.Req;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/7
 */
public class UserLoginReq {

    @NotEmpty(message = "Username can not be empty!")
    private String username;

    @NotNull(message = "Password can not be empty!")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
