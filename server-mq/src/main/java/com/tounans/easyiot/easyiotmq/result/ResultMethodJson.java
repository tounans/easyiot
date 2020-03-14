package com.tounans.easyiot.easyiotmq.result;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class ResultMethodJson {

    private String method = "";
    private Object data = "";

    public ResultMethodJson(String method, Object msg) {
        this.method = method;
        this.data = msg;
    }

    public String toJson(){
        return JSONObject.toJSONString(this);
    }

}


