package com.tounans.easyiot.weboperation.controller.deivce;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fcibook.quick.http.QuickHttp;
import com.tounans.easyiot.common.view.Response;
import com.tounans.easyiot.device.entity.DeviceUart;
import com.tounans.easyiot.device.service.IDeviceUartService;
import com.tounans.easyiot.device.view.DeviceGpioView;
import com.tounans.easyiot.device.view.DeviceUartView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/deviceUart")
public class DeviceUartController {

    @Autowired
    IDeviceUartService deviceUartService;

    @GetMapping("/list")
    public String deviceList(@RequestParam(defaultValue = "1", required = false) int page, Integer userDeviceId, Model model){

        int userId  = 1;

        IPage<DeviceUartView> deviceIPage = deviceUartService.pageByUserParam(page,userDeviceId,userId);
        model.addAttribute("deviceUartList", deviceIPage.getRecords())
                .addAttribute("pageNum", deviceIPage.getTotal() % deviceIPage.getSize() == 0 ? 1 : (deviceIPage.getTotal() / deviceIPage.getSize()+1))
                .addAttribute("current",deviceIPage.getCurrent())
                .addAttribute("total",deviceIPage.getTotal());

        return "admin/deviceUart/list";

    }

    @GetMapping("/toEdit")
    public String toEdit(Integer userUartId, Model model){

        int userId  = 1;

        if (userUartId !=null){
            DeviceUartView deviceUart = deviceUartService.getByUserAndUserUartId(userId, userUartId);
            model.addAttribute("deviceUart",deviceUart);
        }

        return "admin/deviceUart/edit";
    }

    @ResponseBody
    @PostMapping("/edit")
    public Object edit(@RequestBody DeviceUart deviceUart) {
        int userId  = 1;
        deviceUartService.saveOrUpdateUserDeviceUart(userId, deviceUart);
        return new Response().success();
    }

    @ResponseBody
    @PostMapping("/sendUart")
    public Object sendUart(Integer userUartId,String msg){
        Integer userId = 1;

        DeviceUartView deviceUartView = deviceUartService.getByUserAndUserUartId(userId, userUartId);

        if(deviceUartView != null){
            new Thread(new Runnable() {
                //                TODO:与服务服务器通讯需要改这里
                @Override
                public void run() {
                    new QuickHttp().url("http://127.0.0.1:8010/send/uartOutput").addParame("imei",deviceUartView.getImei()).addParame("uart",deviceUartView.getUartId()+"").addParame("msg",msg+"").post().text();
                }
            }).start();
            return new Response().success();
        }

        return new Response().failure();
    }
}
