package com.cosmo.manager.utils.exception;

import com.cosmo.manager.utils.ResultStatus;

public class ResultException extends Exception{
//    业务异常信息
    ResultStatus resultStatus;


    public ResultException() {
        this(ResultStatus.INTERNAL_SERVER_ERROR);
    }

    public ResultException(ResultStatus resultStatus) {
        super(resultStatus.getMsg());
        this.resultStatus = resultStatus;
    }


    public ResultStatus getResultStatus() {
        return resultStatus;
    }
}
