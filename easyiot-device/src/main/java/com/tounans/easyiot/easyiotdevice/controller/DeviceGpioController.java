package com.tounans.easyiot.easyiotdevice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tounans.easyiot.easyiotdevice.entity.DeviceGpio;
import com.tounans.easyiot.easyiotdevice.service.IDeviceGpioService;
import com.tounans.easyiot.easyiotdevice.view.DeviceGpioView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deviceGpio")
public class DeviceGpioController {

    @Autowired
    IDeviceGpioService deviceGpioService;

    @GetMapping("/list")
    public IPage deviceList(@RequestParam(defaultValue = "1", required = false) int page, Integer userDeviceId){

        int userId  = 1;

        IPage<DeviceGpioView> deviceIPage = deviceGpioService.pageByUserParam(page,userDeviceId,userId);
        return deviceIPage;

    }

    @GetMapping("/getDeviceGpio")
    public DeviceGpioView Edit(Integer userGpioId, Model model){

        int userId  = 1;
        DeviceGpioView deviceGpio = deviceGpioService.getByUserAndUserGpioId(userId, userGpioId);
        return deviceGpio;
    }



    @PostMapping( "/edit")
    public boolean edit(@RequestBody DeviceGpio deviceGpio) {
        int userId  = 1;
        deviceGpioService.saveOrUpdateUserDeviceGpio(userId, deviceGpio);
        return true;
    }

    @PostMapping("/sendGpio")
    public Object sendGpio(Integer userGpioId,Integer current){
        Integer userId = 1;

        DeviceGpioView deviceGpioView = deviceGpioService.getByUserAndUserGpioId(userId, userGpioId);

        if(deviceGpioView != null){
            new Thread(new Runnable() {
//                TODO:与服务服务器通讯需要改这里
                @Override
                public void run() {

                }
            }).start();
            return true;
        }

        return false;
    }

}
