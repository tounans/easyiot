package com.tounans.easyiot.weboperation.controller.deivce;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fcibook.quick.http.QuickHttp;
import com.tounans.easyiot.common.view.Response;
import com.tounans.easyiot.device.entity.DeviceGpio;
import com.tounans.easyiot.device.service.IDeviceGpioService;
import com.tounans.easyiot.device.view.DeviceGpioView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/deviceGpio")
public class DeviceGpioController {

    @Autowired
    IDeviceGpioService deviceGpioService;

    @GetMapping("/list")
    public String deviceList(@RequestParam(defaultValue = "1", required = false) int page, Integer userDeviceId, Model model){

        int userId  = 1;

        IPage<DeviceGpioView> deviceIPage = deviceGpioService.pageByUserParam(page,userDeviceId,userId);
        model.addAttribute("deviceGpioList", deviceIPage.getRecords())
                .addAttribute("pageNum", deviceIPage.getTotal() % deviceIPage.getSize() == 0 ? 1 : (deviceIPage.getTotal() / deviceIPage.getSize()+1))
                .addAttribute("current",deviceIPage.getCurrent())
                .addAttribute("total",deviceIPage.getTotal());

        return "admin/deviceGpio/list";

    }

    @GetMapping("/toEdit")
    public String Edit(Integer userGpioId, Model model){

        int userId  = 1;

        if (userGpioId !=null){
            DeviceGpioView deviceGpio = deviceGpioService.getByUserAndUserGpioId(userId, userGpioId);
            model.addAttribute("deviceGpio",deviceGpio);
        }

        return "admin/deviceGpio/edit";
    }



    @ResponseBody
    @PostMapping( "/edit")
    public Object edit(@RequestBody DeviceGpio deviceGpio) {
        int userId  = 1;
        deviceGpioService.saveOrUpdateUserDeviceGpio(userId, deviceGpio);
        return new Response().success();
    }

    @ResponseBody
    @PostMapping("/sendGpio")
    public Object sendGpio(Integer userGpioId,Integer current){
        Integer userId = 1;

        DeviceGpioView deviceGpioView = deviceGpioService.getByUserAndUserGpioId(userId, userGpioId);

        if(deviceGpioView != null){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new QuickHttp().url("http://127.0.0.1:8010/send/gpioOutput").addParame("imei",deviceGpioView.getImei()).addParame("gpio",deviceGpioView.getGpioId()+"").addParame("state",current+"").post().text();
                }
            }).start();
            return new Response().success();
        }

        return new Response().failure();
    }

}
