package com.cosmo.manager.utils;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    //    业务错误码
    private int code;
    //    信息描述
    private String msg;
    //    返回参数
    private T data;

    private Result(ResultStatus resultStatus,T data,String msg){
        this.code = resultStatus.getCode();
        if(msg == null) {
            this.msg = resultStatus.getMsg();
        }else{
            this.msg = msg;
        }
        this.data = data;
    }


    //    业务成功返回的业务代码和描述信息
    public static Result<Void> success(String message){
        return new Result<Void>(ResultStatus.SUCCESS,null,message);
    }



    //    业务成功返回的业务代码、描述和数据
    public static <T> Result<T> success(T data,String message){
        return new Result<T>(ResultStatus.SUCCESS,data,message);
    }


    //    业务成功返回的业务代码、描述和数据
    public static <T> Result<T> success(ResultStatus resultStatus,T data,String message){
        if(resultStatus == null){
            return success(data,message);
        }
        return new Result<T>(resultStatus,data,message);
    }


    //    业务失败返回的业务代码和描述
    public static <T> Result<T> failure(String message){
        return new Result<T>(ResultStatus.INTERNAL_SERVER_ERROR,null,message);
    }



    //    业务失败返回的业务代码和描述
    public static <T> Result<T> failure(ResultStatus resultStatus,String message){
        if(resultStatus == null){
            failure(message);
        }
        return new Result<T>(resultStatus,null,message);
    }


    //    业务失败返回的业务代码和描述
    public static <T> Result<T> failure(ResultStatus resultStatus,T data,String message){
        if(resultStatus == null){
            return new Result<>(ResultStatus.INTERNAL_SERVER_ERROR,null,message);
        }
        return new Result<T>(resultStatus,data,message);
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
