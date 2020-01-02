package com.tounans.easyiot.webservice.controller;

import com.tounans.easyiot.common.view.Response;
import com.tounans.easyiot.device.entity.Device;
import com.tounans.easyiot.device.entity.DeviceGpio;
import com.tounans.easyiot.device.entity.DeviceUart;
import com.tounans.easyiot.device.service.*;
import com.tounans.easyiot.webservice.mqtt.MqttGateway;
import com.tounans.easyiot.webservice.result.ResultMethodJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/send")
public class SendController {

    @Autowired
    MqttGateway mqttGateway;

    @Autowired
    IDeviceService deviceService;

    @Autowired
    IDeviceUartService deviceUartService;

    @Autowired
    IDeviceGpioService deviceGpioService;

    @Autowired
    IDeviceGpioLogService deviceGpioLogService;

    @Autowired
    IDeviceUartLogService deviceUartLogService;

    /**
     * 发送tts
     * @param imei
     * @param msg
     * @return
     */
    @RequestMapping("/tts")
    public Object sendTTS(String imei,String msg){

        Device device = deviceService.getDeviceByImei(imei);
        if(device !=null){
            mqttGateway.sendToMqtt("/request/"+imei,  new ResultMethodJson("tts", msg).toJson());
            return new Response().success();
        }else{
            return  new Response().failure("没有此设备");
        }
    }

    /**
     * 管脚输出
     * @param imei
     * @param gpio
     * @param state
     * @return
     */
    @RequestMapping("/gpioOutput")
    public Object gpioOutput(String imei,Integer gpio,Integer state){
        Device device = deviceService.getDeviceByImei(imei);
        if(device !=null){
            Map<String,Object> data = new HashMap<>();
            data.put("gpio",gpio);
            data.put("state",state);
            mqttGateway.sendToMqtt("/request/"+imei,  new ResultMethodJson("gpioOutput", data).toJson());
            DeviceGpio deviceGpio = deviceGpioService.getByUserDeviceIdAndGpioId(device.getUserDeviceId(), gpio);
            deviceGpio.setCurrent(state);
            deviceGpioService.updateById(deviceGpio);
            deviceGpioLogService.addDowLog(deviceGpio.getUserGpioId(),deviceGpio.getUserId(),deviceGpio.getUserGpioId(),state);
            return new Response().success();
        }else{
            return  new Response().failure("没有此设备");
        }
    }


    /**
     * 获得Gpio当前电平
     * @param imei
     * @param gpio
     * @return
     */
    @RequestMapping("/getGpioVal")
    public Object getGpioVal(String imei,Integer gpio){

        Device device = deviceService.getDeviceByImei(imei);
        if(device !=null){
            mqttGateway.sendToMqtt("/request/"+imei,  new ResultMethodJson("getGpioVal", gpio).toJson());
            return new Response().success();
        }else{
            return  new Response().failure("没有此设备");
        }
    }


    /**
     * uart输出
     * @param imei
     * @param uart
     * @param msg
     * @return
     */
    @RequestMapping("/uartOutput")
    public Object uartOutput(String imei,Integer uart,String msg){
        Device device = deviceService.getDeviceByImei(imei);
        DeviceUart deviceUart = deviceUartService.getReadByUserDeviceIdAndUartId(device.getUserDeviceId(), uart);

        if (deviceUart !=null){

            Map<String,Object> data = new HashMap<>();
            data.put("uart",uart);
            data.put("msg",msg);
            mqttGateway.sendToMqtt("/request/"+imei,  new ResultMethodJson("uartOutput", data).toJson());
            deviceUartLogService.addDowLog(deviceUart.getUserUartId(),deviceUart.getUserId(),deviceUart.getUartId(),msg);

            return new Response().success();
        }

        return new Response().failure();

    }





}
