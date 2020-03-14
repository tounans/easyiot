package com.tounans.easyiot.easyiotwss.controller;

import com.alibaba.fastjson.JSONObject;
import com.tounans.easyiot.common.entity.device.Device;
import com.tounans.easyiot.easyiotwss.wss.UserChannlRel;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class SendController {

    @RequestMapping("/send")
    public Object send(@RequestBody Device device, String msg){
        Integer userId = device.getUserId();
        Channel channel = UserChannlRel.get(userId.toString());

        if (channel != null){
            JSONObject jsonObject = JSONObject.parseObject(msg);
            String method = jsonObject.get("method").toString();

            JSONObject res = new JSONObject();
            res.put("deviceId",device.getUserDeviceId());
            res.put("alias",device.getAlias());
            res.put("imei",device.getImei());
            res.put("method",method);

            if(method.equals("gpioInput")){
                res.put("gpio",jsonObject.getInteger("gpio"));
                res.put("state",jsonObject.getInteger("state"));
            }else if(method.equals("readUart")){
                res.put("uart",jsonObject.getInteger("id"));
                res.put("msg",jsonObject.getString("data"));
            }
            channel.writeAndFlush(new TextWebSocketFrame(res.toJSONString()));

            return true;
        }

        return false;
    }
}
