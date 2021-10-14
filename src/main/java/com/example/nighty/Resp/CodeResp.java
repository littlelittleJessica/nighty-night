package com.example.nighty.Resp;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/13
 */
public class CodeResp {
    //邮箱
    private String email;

    //验证码
    private String code;

    //生成时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date at;

    //用途|枚举[SmsStatusEnum]：USED("U", "已使用"), NOT_USED("N", "未使用")
    private String status;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getAt() {
        return at;
    }

    public void setAt(Date at) {
        this.at = at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
