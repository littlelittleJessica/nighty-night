package com.example.nighty.enums;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/9/19
 */
public enum CodeStatusEnum {

    USED("U", "已使用"),
    NOT_USED("N", "未使用");

    private String code;

    private String desc;

    CodeStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}

