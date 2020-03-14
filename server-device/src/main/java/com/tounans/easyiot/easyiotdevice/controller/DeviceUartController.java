package com.tounans.easyiot.easyiotdevice.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tounans.easyiot.common.entity.device.DeviceUart;
import com.tounans.easyiot.common.entity.user.User;
import com.tounans.easyiot.common.view.device.DeviceUartView;
import com.tounans.easyiot.easyiotdevice.service.IDeviceUartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deviceUart")
public class DeviceUartController {

    @Autowired
    IDeviceUartService deviceUartService;

    /**
     * 获得Device Uart分页
     * @return
     */
    @RequestMapping("/list")
    public Object deviceUartList(@RequestBody String jsonStr, User user) {
        Integer userId = user.getId();
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Integer page = jsonObject.getInteger("page");
        Integer pageSize = jsonObject.getInteger("pageSize");
        Integer userDeviceId = jsonObject.getInteger("userDeviceId");

        IPage<DeviceUartView> deviceIPage = deviceUartService.pageByUserParam(page,pageSize,userDeviceId,userId);
        return deviceIPage;

    }

    /**
     * 更新或者添加Uart
     * @param deviceUart
     * @return
     */
    @RequestMapping("/editAndSaveDeviceUart")
    public Object editAndSaveDeviceUart(@RequestBody DeviceUart deviceUart,User user) {
        Integer userId = user.getId();
        return deviceUartService.saveOrUpdateUserDeviceUart(userId, deviceUart);
    }

    /**
     * 根据用户ID 获得所有Uart
     * @return
     */
    @RequestMapping("/getAll")
    public Object getAll(User user) {
        Integer userId = user.getId();
        return deviceUartService.getListByUserId(userId);
    }

    /**
     * 根据用户的Device UartID 获得
     * @param userId
     * @param userUartId
     * @return
     */
    @RequestMapping("/getDeviceUartById")
    public DeviceUartView getDeviceUartById(Integer userId,Integer userUartId){
        DeviceUartView deviceUart = deviceUartService.getByUserAndUserUartId(userId, userUartId);
        return  deviceUart;
    }



    /**
     * 根据设备ID 和 UartID 获得
     * @param userDeviceId
     * @param uartId
     * @return
     */
    @RequestMapping("/getByUserDeviceIdAndUartId")
    public DeviceUart getByUserDeviceIdAndUartId(Integer userDeviceId, Integer uartId){
        return deviceUartService.getByUserDeviceIdAndUartId(userDeviceId,uartId);
    }



}
