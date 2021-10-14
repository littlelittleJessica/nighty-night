package com.example.nighty.Req;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/14
 */
public class UserResetPasswordReq {

    private String passwordOld;

    private String passwordNew;

    public String getPasswordOld() {
        return passwordOld;
    }

    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }
}
