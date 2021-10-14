package com.example.nighty.exception;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/9/14
 */
public enum BusinessExceptionCode {

    EMAIL_CODE_TOO_FREQUENT("邮件请求过于频繁"),
    EMAIL_CODE_ERROR("邮件验证码错误"),
    EMAIL_CODE_EXPIRED("邮件验证码不存在或已过期，请重新发送邮件"),
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