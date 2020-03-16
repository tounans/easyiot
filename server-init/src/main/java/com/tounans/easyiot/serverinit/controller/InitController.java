package com.tounans.easyiot.serverinit.controller;

import com.tounans.easyiot.common.client.DeviceClient;
import com.tounans.easyiot.common.entity.device.Device;
import com.tounans.easyiot.common.entity.device.DeviceGpio;
import com.tounans.easyiot.common.entity.device.DeviceUart;
import com.tounans.easyiot.common.model.response.CommonCode;
import com.tounans.easyiot.common.model.response.ResponseResult;
import com.tounans.easyiot.common.view.init.DeviceConfigView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InitController {

    @Autowired
    DeviceClient deviceService;



    @GetMapping({"","/"})
    public Object init(@RequestParam("imei")String imei,  @RequestParam("firmwareName")String firmwareName, @RequestParam("version")String version,@RequestParam("deviceKey")String deviceKey){
        Device device = deviceService.getDeviceByImei(imei);

        if (device!=null){
            List<DeviceGpio> deviceGpioList = deviceService.gpioListByUserIdAndDeviceIdAndState(device.getUserId(), device.getUserDeviceId(), true);
            List<DeviceUart> deviceUartList = deviceService.uartListByUserIdAndDeviceIdAndState(device.getUserId(), device.getUserDeviceId(), true);
            return  new ResponseResult().success(new DeviceConfigView(deviceGpioList,deviceUartList));
        }

        return new ResponseResult().failure(CommonCode.FAIL);
    }
}
