package com.tounans.easyiot.easyiotdevice.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tounans.easyiot.easyiotdevice.entity.Device;
import com.tounans.easyiot.easyiotdevice.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    IDeviceService deviceService;

    @GetMapping("/list")
    public IPage deviceList(@RequestParam(defaultValue = "1", required = false) int page, String imei){

        int userId  = 1;

        IPage<Device> deviceIPage = deviceService.pageByUserParam(page,imei,userId);

        return deviceIPage;
    }

    @GetMapping("/getDevice")
    public Device Edit(Integer userDeviceId){

        int userId  = 1;

        Device device = deviceService.getByUserAndUserDeviceId(userId, userDeviceId);

        return device;
    }

    @PostMapping( "/edit")
    public boolean edit(@RequestBody Device device) {
        int userId  = 1;
        deviceService.saveOrUpdateUserDevice(userId, device);
        return true;
    }

    @PostMapping( "/existence")
    public boolean edit(@RequestBody String body) {
        JSONObject jsonObject = JSONObject.parseObject(body);

        Integer count = deviceService.countByImei(jsonObject.getString("imei"));

        if(count != 1){
            return true;
        }else {
            return false;
        }
    }

    @PostMapping( "/getAll")
    public List<Device> getAll() {
        Integer userId = 1;
        List<Device> listByUserId = deviceService.getListByUserId(userId);
        return  listByUserId;
    }

}
