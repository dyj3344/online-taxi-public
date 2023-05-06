package com.mashibing.common.constant;

public enum CommonStatusEnum {

    SUCCESS(1,"success"),
    FAILED(0,"failed")
    ;

    private int code;

    private String  value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
