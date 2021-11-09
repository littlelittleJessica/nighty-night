package com.example.nighty.exception;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/9/14
 */
public enum BusinessExceptionCode {

    EMAIL_CODE_TOO_FREQUENT("email send too frequent"),
    EMAIL_CODE_ERROR("verification code is incorrect"),
    EMAIL_CODE_EXPIRED("verification code is expired or invalid"),
    LOGIN_USER_ERROR("Wrong username or password");
    ;

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}