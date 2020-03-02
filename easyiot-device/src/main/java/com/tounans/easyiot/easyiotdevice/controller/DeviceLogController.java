package com.tounans.easyiot.easyiotdevice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tounans.easyiot.easyiotdevice.service.IDeviceGpioLogService;
import com.tounans.easyiot.easyiotdevice.service.IDeviceUartLogService;
import com.tounans.easyiot.easyiotdevice.view.DeviceGpioLogView;
import com.tounans.easyiot.easyiotdevice.view.DeviceUartLogView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/gpioList")
    public IPage gpioList(@RequestParam(defaultValue = "1", required = false) int page, Integer userGpioId, Model model){

        int userId  = 1;

        IPage<DeviceGpioLogView> deviceGpioLogViewIPage = deviceGpioLogService.pageByUserParam(page, userGpioId, userId);
        return  deviceGpioLogViewIPage;

    }

    @GetMapping("/uartList")
    public IPage uartList(@RequestParam(defaultValue = "1", required = false) int page, Integer userUartId, Model model){

        int userId  = 1;

        IPage<DeviceUartLogView> deviceUartLogViewIPage = deviceUartLogService.pageByUserParam(page, userUartId, userId);
        return deviceUartLogViewIPage;

    }
}
