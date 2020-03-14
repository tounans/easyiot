package com.tounans.easyiot.common.model.auth;

import com.tounans.easyiot.common.model.response.ResultCode;

public enum AuthCode implements ResultCode {
    AUTH_USERNAME_NONE("请输入账号！"),
    AUTH_PASSWORD_NONE("请输入密码！"),
    AUTH_VERIFYCODE_NONE("请输入验证码！"),
    AUTH_ACCOUNT_NOTEXISTS("账号不存在！"),
    AUTH_CREDENTIAL_ERROR("账号或密码错误！"),
    AUTH_LOGIN_ERROR("登陆过程出现异常请尝试重新操作！"),
    AUTH_LOGIN_APPLYTOKEN_FAIL("申请令牌失败！"),
    AUTH_LOGIN_TOKEN_SAVEFAIL("存储令牌失败！"),
    AUTH_LOGOUT_FAIL("退出失败！");

    String message;
    private AuthCode( String message){
        this.message = message;
    }


    @Override
    public String message() {
        return message;
    }
}
