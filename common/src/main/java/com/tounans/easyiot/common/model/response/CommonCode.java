package com.tounans.easyiot.common.model.response;

import lombok.ToString;

/**
 * @Author: mrt.
 * @Description:
 * @Date:Created in 2018/1/24 18:33.
 * @Modified By:
 */

@ToString
public enum CommonCode implements ResultCode{
    INVALID_PARAM("非法参数！"),
    SUCCESS("操作成功！"),
    FAIL("操作失败！"),
    UNAUTHENTICATED("此操作需要登陆系统！"),
    UNAUTHORISE("权限不足，无权操作！"),
    SERVER_ERROR("抱歉，系统繁忙，请稍后重试！");

    //提示信息
    String message;
    private CommonCode(String message){
        this.message = message;
    }


    @Override
    public String message() {
        return message;
    }
}
