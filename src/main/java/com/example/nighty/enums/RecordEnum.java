package com.example.nighty.enums;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/23
 */
public enum RecordEnum {

    //emotion
    RELAX("R", "Relax and comfort"),
    ANXIETY("A", "Anxious and depressed"),
    STRESSFUL("S", "Stressed out"),
    TIRED("T", "Tired and exhausted"),
    NONE("N", "None of them"),

    //dream
    NO("N", "No dreams"),
    GOOD("G", "Had sweet dreams"),
    COMMON("C", "Had common dreams"),
    NIGHTMARES("M", "Had nightmares"),

    //evaluation
    AWESOME("A", "Awesome"),
    Good("G", "Good"),
    BAD("B", "Bad"),
    TERRIBLE("T", "Terrible");

    private String code;

    private String desc;

    RecordEnum(String code, String desc) {
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
