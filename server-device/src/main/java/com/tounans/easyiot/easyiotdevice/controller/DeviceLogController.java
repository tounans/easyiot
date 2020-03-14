package com.tounans.easyiot.easyiotdevice.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tounans.easyiot.common.view.device.DeviceGpioLogView;
import com.tounans.easyiot.common.view.device.DeviceUartLogView;
import com.tounans.easyiot.easyiotdevice.service.IDeviceGpioLogService;
import com.tounans.easyiot.easyiotdevice.service.IDeviceUartLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deviceLog")
public class DeviceLogController {

    @Autowired
    IDeviceUartLogService deviceUartLogService;

    @Autowired
    IDeviceGpioLogService deviceGpioLogService;

    @RequestMapping("/deviceGpioLogList")
    public Object deviceGpioLogList(@RequestBody String jsonStr){
        Integer userId = 1;
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Integer page = jsonObject.getInteger("page");
        Integer pageSize = jsonObject.getInteger("pageSize");
        Integer userGpioId = jsonObject.getInteger("userGpioId");

        IPage<DeviceGpioLogView> deviceGpioLogViewIPage = deviceGpioLogService.pageByUserParam(page, pageSize,userGpioId, userId);
        return  deviceGpioLogViewIPage;

    }

    @RequestMapping("/deviceUartLogList")
    public Object deviceUartLogList(@RequestBody String jsonStr){
        Integer userId = 1;
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Integer page = jsonObject.getInteger("page");
        Integer pageSize = jsonObject.getInteger("pageSize");
        Integer userUartId = jsonObject.getInteger("userUartId");

        IPage<DeviceUartLogView> deviceUartLogViewIPage = deviceUartLogService.pageByUserParam(page, pageSize, userUartId, userId);
        return deviceUartLogViewIPage;

    }

    /**
     * 添加日志
     * @param deviceId
     * @param userId
     * @param gpioId
     * @param type 0上传 1下发
     * @param state
     * @return
     */
    @RequestMapping("/addGpioLog")
    public boolean addGpioLog(Integer deviceId,Integer userId, Integer gpioId,Integer type,Integer state){
        return deviceGpioLogService.addLog(deviceId, userId, gpioId, type, state);
    }


    /**
     *
     * @param deviceId
     * @param userId
     * @param uartId
     * @param type 0上传 1下发
     * @param data
     * @return
     */
    @RequestMapping("/addUartLog")
    public boolean addUartLog(Integer deviceId,Integer userId, Integer uartId,Integer type,String data){
        return deviceUartLogService.addLog(deviceId, userId, uartId, type, data);
    }
}
