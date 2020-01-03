package com.tounans.easyiot.weboperation.controller.deivce;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tounans.easyiot.common.view.Response;
import com.tounans.easyiot.device.entity.Device;
import com.tounans.easyiot.device.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/device")
public class DeviceController {

    @Autowired
    IDeviceService deviceService;

    @GetMapping("/list")
    public String deviceList(@RequestParam(defaultValue = "1", required = false) int page, String imei, Model model){

        int userId  = 1;

        IPage<Device> deviceIPage = deviceService.pageByUserParam(page,imei,userId);

        model.addAttribute("deviceList", deviceIPage.getRecords())
                .addAttribute("pageNum", deviceIPage.getTotal() % deviceIPage.getSize() == 0 ? 1 : (deviceIPage.getTotal() / deviceIPage.getSize()+1))
                .addAttribute("current",deviceIPage.getCurrent())
                .addAttribute("total",deviceIPage.getTotal());

        return "admin/device/list";
    }

    @GetMapping("/toEdit")
    public String Edit(Integer userDeviceId, Model model){

        int userId  = 1;

        if (userDeviceId !=null){
            Device device = deviceService.getByUserAndUserDeviceId(userId, userDeviceId);
            model.addAttribute("device",device);
        }

        return "admin/device/edit";
    }

    @ResponseBody
    @PostMapping( "/edit")
    public Object edit(@RequestBody Device device) {
        int userId  = 1;
        deviceService.saveOrUpdateUserDevice(userId, device);
        return new Response().success();
    }

    @ResponseBody
    @PostMapping( "/existence")
    public Object edit(@RequestBody String body) {
        JSONObject jsonObject = JSONObject.parseObject(body);

        Integer count = deviceService.countByImei(jsonObject.getString("imei"));

        if(count != 1){
            return new Response().success();
        }else {
            return new Response().failure("设备已被绑定！");
        }
    }


    @ResponseBody
    @PostMapping( "/getAll")
    public Object getAll() {
        Integer userId = 1;
        List<Device> listByUserId = deviceService.getListByUserId(userId);
        return new Response().success(listByUserId);
    }

}
