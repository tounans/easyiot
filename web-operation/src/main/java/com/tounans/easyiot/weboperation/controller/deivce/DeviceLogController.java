package com.tounans.easyiot.weboperation.controller.deivce;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tounans.easyiot.device.service.IDeviceGpioLogService;
import com.tounans.easyiot.device.service.IDeviceUartLogService;
import com.tounans.easyiot.device.view.DeviceGpioLogView;
import com.tounans.easyiot.device.view.DeviceUartLogView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/deviceLog")
public class DeviceLogController {

    @Autowired
    IDeviceUartLogService deviceUartLogService;

    @Autowired
    IDeviceGpioLogService deviceGpioLogService;

    @GetMapping("/gpioList")
    public String gpioList(@RequestParam(defaultValue = "1", required = false) int page, Integer userGpioId, Model model){

        int userId  = 1;

        IPage<DeviceGpioLogView> deviceGpioLogViewIPage = deviceGpioLogService.pageByUserParam(page, userGpioId, userId);
        model.addAttribute("deviceGpioLogList", deviceGpioLogViewIPage.getRecords())
                .addAttribute("pageNum", deviceGpioLogViewIPage.getTotal() % deviceGpioLogViewIPage.getSize() == 0 ? 1 : (deviceGpioLogViewIPage.getTotal() / deviceGpioLogViewIPage.getSize()+1))
                .addAttribute("current",deviceGpioLogViewIPage.getCurrent())
                .addAttribute("total",deviceGpioLogViewIPage.getTotal());

        return "admin/deviceLog/gpioList";

    }

    @GetMapping("/uartList")
    public String uartList(@RequestParam(defaultValue = "1", required = false) int page, Integer userUartId, Model model){

        int userId  = 1;

        IPage<DeviceUartLogView> deviceUartLogViewIPage = deviceUartLogService.pageByUserParam(page, userUartId, userId);
        model.addAttribute("deviceUartLogList", deviceUartLogViewIPage.getRecords())
                .addAttribute("pageNum", deviceUartLogViewIPage.getTotal() % deviceUartLogViewIPage.getSize() == 0 ? 1 : (deviceUartLogViewIPage.getTotal() / deviceUartLogViewIPage.getSize()+1))
                .addAttribute("current",deviceUartLogViewIPage.getCurrent())
                .addAttribute("total",deviceUartLogViewIPage.getTotal());

        return "admin/deviceLog/uartList";

    }
}
