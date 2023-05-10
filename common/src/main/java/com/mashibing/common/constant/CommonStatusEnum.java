package com.mashibing.common.constant;

public enum CommonStatusEnum {
    //验证码错误提示:1000-1099

    /*
     * 验证码不正确
     * */
    VERIFICATION_CODE_ERROR(1099,"验证码不正确"),
    /*
     * 验证码已过期
     * */
    VERIFICATION_CODE_EXPIRE(1098,"验证码已过期"),
    /*
    * 成功
    * */
    SUCCESS(1,"success"),
    /*
     * 失败
     * */
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
