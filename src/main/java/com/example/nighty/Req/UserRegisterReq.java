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

    @NotEmpty(message = "Email can not be empty!")
    private String email;

    @NotNull(message = "Password can not be empty!")
    private String password;

    @NotNull(message = "Verification code can not be empty!")
    private String code;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
