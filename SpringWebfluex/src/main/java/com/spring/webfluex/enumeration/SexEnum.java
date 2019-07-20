package com.spring.webfluex.enumeration;

public enum SexEnum {
    MALE(1, "男"),

    FEMALE(0, "女");

    private int code;

    private String name;
    SexEnum(){

    }

    SexEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private static SexEnum getSexEnum(int code) {
        SexEnum[] enums = SexEnum.values();
        for (SexEnum item : enums) {
            if (item.code == code) {
                return item;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SexEnum{" + "code=" + code + ", name='" + name + '\'' + '}';
    }
}
