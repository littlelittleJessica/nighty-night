package com.example.nighty.common;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/18
 */
public enum VoiceCategory {

    MUSIC("M", "Light Music"),
    STORY("S", "Sleep Story"),
    WHITE_NOISE("W", "White Noise");

    private String code;

    private String desc;

    VoiceCategory(String code, String desc) {
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
