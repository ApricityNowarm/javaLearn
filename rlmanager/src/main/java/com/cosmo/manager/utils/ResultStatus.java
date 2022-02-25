package com.cosmo.manager.utils;

import org.springframework.http.HttpStatus;

public enum ResultStatus {

    SUCCESS(HttpStatus.OK,200,"OK"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST,400,"Bad Request"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,500,"Internal Server Error");
//    返回的Http状态码
    private HttpStatus httpStatus;
//    业务异常码
    private int code;
//   业务异常信息描述
    private String msg;

    ResultStatus(HttpStatus httpStatus, int code, String msg) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.msg = msg;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "ResultStatus{" +
                "httpStatus=" + httpStatus +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
