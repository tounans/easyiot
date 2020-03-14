package com.tounans.easyiot.common.client;

import com.tounans.easyiot.common.entity.device.Device;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "server-wss")
public interface WssClient {
    @RequestMapping("/send")
    Object send(@RequestBody Device device,@RequestParam("msg")  String msg);
}
