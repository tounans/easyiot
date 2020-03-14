package com.tounans.easyiot.common.model.response;


import com.alibaba.fastjson.JSON;

public class ResponseResult {
    private static final String OK = "ok";
    private static final String ERROR = "error";

    private Meta meta;     // 元数据
    private Object data;   // 响应内容

    public ResponseResult success() {
        this.meta = new Meta(true, OK);
        return this;
    }

    public ResponseResult success(Object data) {
        this.meta = new Meta(true, OK);
        this.data = data;
        return this;
    }

    public ResponseResult failure(CommonCode serverError) {
        this.meta = new Meta(false, serverError.message());
        return this;
    }

    public ResponseResult failure(String message) {
        this.meta = new Meta(false, message);
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
