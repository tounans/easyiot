package com.tounans.easyiot.common.exception;

import com.tounans.easyiot.common.model.response.ResultCode;

/**
 **/
public class ExceptionCast {

    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
