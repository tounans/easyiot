package com.tounans.easyiot.easyiotdevice.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tounans.easyiot.common.entity.device.DeviceGpio;
import com.tounans.easyiot.common.entity.user.User;
import com.tounans.easyiot.common.view.device.DeviceGpioView;
import com.tounans.easyiot.easyiotdevice.service.IDeviceGpioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deviceGpio")
public class DeviceGpioController {

    @Autowired
    IDeviceGpioService deviceGpioService;

    /**
     * 小程序用
     * 获得deviceGpio 分页的
     * @return
     */
    @RequestMapping("/list")
    public Object deviceGpioList(@RequestBody String jsonStr, User user) {
        Integer userId = user.getId();
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Integer page = jsonObject.getInteger("page");
        Integer pageSize = jsonObject.getInteger("pageSize");
        Integer userDeviceId = jsonObject.getInteger("userDeviceId");


        IPage<DeviceGpioView> deviceIPage = deviceGpioService.pageByUserParam(page,pageSize,userDeviceId,userId);
        return deviceIPage;

    }


    /**
     * 小程序用
     * 更新或者添加GPIO
     * @param deviceGpio
     * @return
     */
    @RequestMapping( "/editAndSaveDeviceGpio")
    public Object editAndSaveDeviceGpio(@RequestBody DeviceGpio deviceGpio,User user) {
        Integer userId = null;
        if (user.getId() == null){
            userId = deviceGpio.getUserId();
        }else{
            userId = user.getId();
        }
        return deviceGpioService.saveOrUpdateUserDeviceGpio(userId, deviceGpio);
    }

    /**
     * 小程序用
     * 根据用户ID 获得所有GPIO
     * @return
     */
    @RequestMapping("/getAll")
    public Object getAll(User user) {
        Integer userId = user.getId();
        return deviceGpioService.getListByUserId(userId);
    }

    /**
     * 根据设备ID 和 gpioID 获得
     * @param userDeviceId
     * @param gpioId
     * @return
     */
    @RequestMapping("/getByUserDeviceIdAndGpioId")
    public DeviceGpio getByUserDeviceIdAndGpioId(Integer userDeviceId, Integer gpioId){
        return deviceGpioService.getByUserDeviceIdAndGpioId(userDeviceId, gpioId);
    }


    /**
     * 更新GPIO状态
     * @param userDeviceId
     * @param userId
     * @param gpio
     * @param state
     * @return
     */
    @RequestMapping("/updateCurrent")
    public boolean updateCurrent(Integer userDeviceId, Integer userId, Integer gpio, Integer state){
        return deviceGpioService.updateCurrent(userDeviceId, userId, gpio, state);
    }

    /**
     * 根据Devicegpio id 获得
     * @param userId
     * @param userGpioId
     * @return
     */
    @RequestMapping("/getDeviceGpioById")
    public DeviceGpioView getDeviceGpioById(Integer userId,Integer userGpioId){
        DeviceGpioView deviceGpio = deviceGpioService.getByUserAndUserGpioId(userId, userGpioId);
        return deviceGpio;
    }





}
