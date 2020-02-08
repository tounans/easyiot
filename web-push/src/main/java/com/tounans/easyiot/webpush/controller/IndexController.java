package com.tounans.easyiot.webpush.controller;


import com.tounans.easyiot.common.view.Response;
import com.tounans.easyiot.device.entity.Device;
import com.tounans.easyiot.device.service.IDeviceService;
import com.tounans.easyiot.push.entity.PushHttp;
import com.tounans.easyiot.push.service.IPushHttpService;
import com.tounans.easyiot.webpush.service.IPushService;
import com.tounans.easyiot.webpush.wss.UserChannlRel;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    IDeviceService deviceService;

    @Autowired
    IPushHttpService pushHttpService;

    @Autowired
    IPushService pushService;


    @RequestMapping({"","/"})
    public Object index(String imei,String data){

        Device device = deviceService.getDeviceByImei(imei);
        if (device!=null){
            List<PushHttp> pushHttpList = pushHttpService.listByUserId(device.getUserId());

            if (pushHttpList.size()<0){
                for (PushHttp pushHttp : pushHttpList) {
//            TODO:HTTP 回调这里 可以优化
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            pushService.pushHttp(imei,data,pushHttp);
                        }
                    }).start();
                }
            }

            Channel channel = UserChannlRel.get(device.getUserId() + "");
            if(channel !=null){
                channel.writeAndFlush(new TextWebSocketFrame(data));
            }


            return new Response().success();
        }




        return new Response().failure();
    }
}
