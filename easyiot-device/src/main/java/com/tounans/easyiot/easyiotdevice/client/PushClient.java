package com.tounans.easyiot.easyiotdevice.client;

import com.tounans.easyiot.easyiotdevice.client.hystrix.PushClientHystrix;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "easyiot-push",fallback = PushClientHystrix.class)
public interface PushClient {
}
