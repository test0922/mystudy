package com.jay.common.exception;

public enum BizCodeEnume {
    UNKNOW_EXCEPTION(10000,"系統未知異常"),
    VALID_EXCEPTION(10000, "參數格式校驗異常");
    private int code;
    private String msg;

    BizCodeEnume(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
