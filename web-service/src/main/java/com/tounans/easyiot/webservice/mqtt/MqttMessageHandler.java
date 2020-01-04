package com.tounans.easyiot.webservice.mqtt;

import com.alibaba.fastjson.JSONObject;
import com.fcibook.quick.http.QuickHttp;
import com.tounans.easyiot.device.entity.Device;
import com.tounans.easyiot.device.entity.DeviceGpio;
import com.tounans.easyiot.device.entity.DeviceUart;
import com.tounans.easyiot.device.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * 处理mqtt收到的请求
 */
@Component
public class MqttMessageHandler implements MessageHandler {


    @Autowired
    IDeviceService deviceService;

    @Autowired
    IDeviceGpioService deviceGpioService;

    @Autowired
    IDeviceGpioLogService deviceGpioLogService;

    @Autowired
    IDeviceUartService deviceUartService;

    @Autowired
    IDeviceUartLogService deviceUartLogService;


    @Override
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleMessage(Message<?> message) throws MessagingException {
        String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
        String imei = topic.substring(topic.lastIndexOf("/") + 1, topic.length());
        String payload = message.getPayload().toString();
        processing(imei, payload);
    }


    /**
     * 处理方法MQTT收到的信息
     *
     * @param imei    设备IMEI
     * @param message 设备消息
     */
    public void processing(String imei, String message) {
        Device device = deviceService.getDeviceByImei(imei);
        JSONObject jsonObject = JSONObject.parseObject(message);
        String method = jsonObject.get("method").toString();

        if(method.equals("gpioInput")){
            //管脚中断
            Integer gpio = jsonObject.getInteger("gpio");
            Integer state = jsonObject.getInteger("state");

            DeviceGpio deviceGpio = deviceGpioService.getByUserDeviceIdAndGpioId(device.getUserDeviceId(), gpio);
            deviceGpioService.updateCurrent(deviceGpio.getDeviceId(),deviceGpio.getUserId(),deviceGpio.getGpioId(),state);
            deviceGpioLogService.addUpLog(device.getUserDeviceId(),device.getUserId(),deviceGpio.getUserGpioId(),state);
            new Thread(new Runnable() {
                // TODO:与回调服务器通讯需要改这里
                @Override
                public void run() {
                    new QuickHttp().url("http://127.0.0.1:8040/").addParame("imei",device.getImei()).addParame("data",message).post().text();
                }
            }).start();

        }else if(method.equals("readUart")){
            //{"id":1,"method":"readUart","data":"abcdefg"}
            Integer id = jsonObject.getInteger("id");
            String data = jsonObject.getString("data");

            DeviceUart deviceUart = deviceUartService.getReadByUserDeviceIdAndUartId(device.getUserDeviceId(), id);
            deviceUartLogService.addUpLog(device.getUserDeviceId(),device.getUserId(),deviceUart.getUserUartId(),data);

            new Thread(new Runnable() {
                // TODO:与回调服务器通讯需要改这里
                @Override
                public void run() {
                    new QuickHttp().url("http://127.0.0.1:8040/").addParame("imei",device.getImei()).addParame("data",message).post().text();
                }
            }).start();
        }
    }



}
