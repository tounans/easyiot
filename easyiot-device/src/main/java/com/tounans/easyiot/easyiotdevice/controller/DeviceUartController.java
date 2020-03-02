package com.tounans.easyiot.easyiotdevice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tounans.easyiot.easyiotdevice.entity.DeviceUart;
import com.tounans.easyiot.easyiotdevice.service.IDeviceUartService;
import com.tounans.easyiot.easyiotdevice.view.DeviceUartView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deviceUart")
public class DeviceUartController {

    @Autowired
    IDeviceUartService deviceUartService;

    @GetMapping("/list")
    public IPage deviceList(@RequestParam(defaultValue = "1", required = false) int page, Integer userDeviceId, Model model){

        int userId  = 1;

        IPage<DeviceUartView> deviceIPage = deviceUartService.pageByUserParam(page,userDeviceId,userId);
        return deviceIPage;

    }

    @GetMapping("/getDeviceUart")
    public DeviceUartView toEdit(Integer userUartId, Model model){

        int userId  = 1;


            DeviceUartView deviceUart = deviceUartService.getByUserAndUserUartId(userId, userUartId);
         return  deviceUart;
    }

    @PostMapping("/edit")
    public boolean edit(@RequestBody DeviceUart deviceUart) {
        int userId  = 1;
        deviceUartService.saveOrUpdateUserDeviceUart(userId, deviceUart);
        return true;
    }

    @PostMapping("/sendUart")
    public boolean sendUart(Integer userUartId,String msg){
        Integer userId = 1;

        DeviceUartView deviceUartView = deviceUartService.getByUserAndUserUartId(userId, userUartId);

        if(deviceUartView != null){
            new Thread(new Runnable() {
                //                TODO:与服务服务器通讯需要改这里
                @Override
                public void run() {
//                    new QuickHttp().url("http://127.0.0.1:8010/send/uartOutput").addParame("imei",deviceUartView.getImei()).addParame("uart",deviceUartView.getUartId()+"").addParame("msg",msg+"").post().text();
                }
            }).start();
            return true;
        }

        return false;
    }
}
