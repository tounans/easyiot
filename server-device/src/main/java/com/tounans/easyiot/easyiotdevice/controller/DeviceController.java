package com.tounans.easyiot.easyiotdevice.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tounans.easyiot.common.entity.device.Device;
import com.tounans.easyiot.common.entity.user.User;
import com.tounans.easyiot.easyiotdevice.service.IDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;


@RestController
@Api(tags = "用户管理相关接口")
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    IDeviceService deviceService;


    /**
     * 小程序用
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Object deviceList(@RequestBody String jsonStr,User user){

        Integer userId = user.getId();
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Integer page = jsonObject.getInteger("page");
        Integer pageSize = jsonObject.getInteger("pageSize");
        String imei = jsonObject.getString("imei");

        IPage<Device> deviceIPage = deviceService.pageByUserParam(page,pageSize,imei,userId);

        return deviceIPage;
    }

    /**
     * 小程序用
     * @return
     */
    @RequestMapping(value = "/getDeviceByIdImei",method = RequestMethod.POST)
    public Object getDeviceByIdImei(@RequestBody String jsonStr,User user){
        Integer userId = user.getId();
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        String imei = jsonObject.getString("imei");
        Device device = deviceService.getByUserAndImei(userId, imei);
        return device;
    }

    /**
     * 小程序用
     * @param device
     * @return
     */
    @RequestMapping(value =  "/editAndSaveDevice",method = RequestMethod.POST)
    public Object editAndSaveDevice(@RequestBody Device device,User user) {
        Integer userId = user.getId();
        return deviceService.saveOrUpdateUserDevice(userId, device);
    }

    @RequestMapping(value =  "/getAll",method = RequestMethod.POST)
    public Object getAll(User user) {
        Integer userId = user.getId();
        List<Device> listByUserId = deviceService.getListByUserId(userId);
        return  listByUserId;
    }

    @RequestMapping(value =  "/existence",method = RequestMethod.POST)
    public Object existence(String imei) {
        Integer count = deviceService.countByImei(imei);
        if(count != 1){
            return true;
        }else {
            return false;
        }
    }


    @RequestMapping(value = "/getDeviceById",method = RequestMethod.POST)
    public Device getDeviceById(Integer userId,Integer userDeviceId){
        Device device = deviceService.getByUserAndUserDeviceId(userId, userDeviceId);
        return device;
    }


    @RequestMapping(value = "/getDeviceByUserIdAndImei",method = RequestMethod.POST)
    public Device getDeviceByUserIdAndImei(Integer userId,String imei){

        Device device = deviceService.getByUserAndImei(userId, imei);

        return device;
    }

    @RequestMapping(value = "/getDeviceByImei",method = RequestMethod.POST)
    public Device getDeviceByImei(String imei){

        Device device = deviceService.getDeviceByImei(imei);

        return device;
    }







}
