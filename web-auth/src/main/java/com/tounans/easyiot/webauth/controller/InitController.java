package com.tounans.easyiot.webauth.controller;

import com.tounans.easyiot.common.view.Response;
import com.tounans.easyiot.device.entity.Device;
import com.tounans.easyiot.device.entity.DeviceGpio;
import com.tounans.easyiot.device.entity.DeviceUart;
import com.tounans.easyiot.device.service.IDeviceGpioService;
import com.tounans.easyiot.device.service.IDeviceService;
import com.tounans.easyiot.device.service.IDeviceUartService;
import com.tounans.easyiot.webauth.view.DeviceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/init")
public class InitController {

    @Autowired
    IDeviceService deviceService;

    @Autowired
    IDeviceUartService deviceUartService;

    @Autowired
    IDeviceGpioService deviceGpioService;


//    @PostMapping({"","/"})
    @GetMapping({"","/"})
    public Object init(@RequestParam("imei")String imei,  @RequestParam("firmwareName")String firmwareName, @RequestParam("version")String version,@RequestParam("deviceKey")String deviceKey){
        Device device = deviceService.getDeviceByImei(imei);

        if (device!=null){
            List<DeviceGpio> deviceGpioList = deviceGpioService.listByUserIdAndDeviceIdAndState(device.getUserId(), device.getUserDeviceId(), true);
            List<DeviceUart> deviceUartList = deviceUartService.listByUserIdAndDeviceIdAndState(device.getUserId(), device.getUserDeviceId(), true);
            return  new Response().success(new DeviceConfig(deviceGpioList,deviceUartList));
        }


        return new Response().failure();
    }
}
