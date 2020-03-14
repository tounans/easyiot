package com.tounans.easyiot.easyiotmq.controller;

import com.tounans.easyiot.common.client.DeviceClient;
import com.tounans.easyiot.common.entity.device.Device;
import com.tounans.easyiot.common.entity.device.DeviceGpio;
import com.tounans.easyiot.common.entity.device.DeviceUart;
import com.tounans.easyiot.easyiotmq.mqtt.MqttGateway;
import com.tounans.easyiot.easyiotmq.result.ResultMethodJson;
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
    DeviceClient deviceClient;



    /**
     * 发送tts
     * @param userId
     * @param imei
     * @param msg
     * @return
     */
    @RequestMapping("/tts")
    public Object sendTTS(Integer userId,String imei,String msg){
        Device device = deviceClient.getDeviceByUserIdAndImei(userId,imei);
        if(device !=null){
            mqttGateway.sendToMqtt("/request/"+imei,  new ResultMethodJson("tts", msg).toJson());
            return true;
        }else{
            return false;
        }
    }

    /**
     * 管脚输出
     * @param userId
     * @param imei
     * @param gpio
     * @param state
     * @return
     */
    @RequestMapping("/gpioOutput")
    public Object gpioOutput(Integer userId,String imei,Integer gpio,Integer state){
        Device device = deviceClient.getDeviceByUserIdAndImei(userId,imei);
        if(device !=null){
            Map<String,Object> data = new HashMap<>();
            data.put("gpio",gpio);
            data.put("state",state);
            mqttGateway.sendToMqtt("/request/"+imei,  new ResultMethodJson("gpioOutput", data).toJson());
            DeviceGpio deviceGpio = deviceClient.getByUserDeviceIdAndGpioId(device.getUserDeviceId(), gpio);
            deviceGpio.setCurrent(state);
            deviceClient.editAndSaveDeviceGpio(device.getUserId(),deviceGpio);
            deviceClient.addGpioLog(deviceGpio.getDeviceId(),deviceGpio.getUserId(),deviceGpio.getUserGpioId(),1,state);
            return true;
        }else{
            return false;
        }
    }


    /**
     * 获得Gpio当前电平
     * @param userId
     * @param imei
     * @param gpio
     * @return
     */
    @RequestMapping("/getGpioVal")
    public boolean getGpioVal(Integer userId,String imei,Integer gpio){
        Device device = deviceClient.getDeviceByUserIdAndImei(userId,imei);
        if(device !=null){
            mqttGateway.sendToMqtt("/request/"+imei,  new ResultMethodJson("getGpioVal", gpio).toJson());
            return true;
        }else{
            return false;
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
    public boolean uartOutput(Integer userId,String imei,Integer uart,String msg){
        Device device = deviceClient.getDeviceByUserIdAndImei(userId,imei);
        DeviceUart deviceUart = deviceClient.getByUserDeviceIdAndUartId(device.getUserDeviceId(), uart);

        if (deviceUart !=null){

            Map<String,Object> data = new HashMap<>();
            data.put("uart",uart);
            data.put("msg",msg);
            mqttGateway.sendToMqtt("/request/"+imei,  new ResultMethodJson("uartOutput", data).toJson());
            deviceClient.addUartLog(deviceUart.getDeviceId(),deviceUart.getUserId(),deviceUart.getUserUartId(),1,msg);
            return true;
        }
        return false;

    }





}
