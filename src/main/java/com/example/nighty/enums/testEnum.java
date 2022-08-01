package com.example.nighty.enums;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/23
 */
public enum testEnum {

    TEST_ENUM("test", "test");


    private String code;

    private String desc;

    testEnum(String code, String desc) {
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
