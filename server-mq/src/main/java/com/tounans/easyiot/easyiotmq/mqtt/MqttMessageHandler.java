package com.tounans.easyiot.easyiotmq.mqtt;

import com.alibaba.fastjson.JSONObject;
import com.tounans.easyiot.common.client.DeviceClient;
import com.tounans.easyiot.common.client.WssClient;
import com.tounans.easyiot.common.entity.device.Device;
import com.tounans.easyiot.common.entity.device.DeviceGpio;
import com.tounans.easyiot.common.entity.device.DeviceUart;
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
    DeviceClient deviceClient;

    @Autowired
    WssClient wssClient;

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
    protected void processing(String imei, String message) {
        Device device = deviceClient.getDeviceByImei(imei);
        JSONObject jsonObject = JSONObject.parseObject(message);
        String method = jsonObject.get("method").toString();
        wssClient.send(device,message);
        if(method.equals("gpioInput")){
            //管脚中断
            Integer gpioId = jsonObject.getInteger("gpio");
            Integer state = jsonObject.getInteger("state");

            DeviceGpio deviceGpio = deviceClient.getByUserDeviceIdAndGpioId(device.getUserDeviceId(), gpioId);
            deviceClient.updateCurrent(deviceGpio.getDeviceId(),deviceGpio.getUserId(),deviceGpio.getGpioId(),state);


//            new Thread(new Runnable() {
//                // TODO:与回调服务器通讯需要改这里
//                @Override
//                public void run() {
//                    new QuickHttp().url("http://127.0.0.1:8040/").addParame("imei",device.getImei()).addParame("data",message).post().text();
//                }
//            }).start();

        }else if(method.equals("readUart")){
            //{"id":1,"method":"readUart","data":"abcdefg"}
            Integer uartId = jsonObject.getInteger("id");
            String data = jsonObject.getString("data");

            DeviceUart deviceUart = deviceClient.getByUserDeviceIdAndUartId(device.getUserDeviceId(), uartId);
            deviceClient.addUartLog(device.getUserDeviceId(),device.getUserId(),deviceUart.getUserUartId(),0,data);
//
//            new Thread(new Runnable() {
//                // TODO:与回调服务器通讯需要改这里
//                @Override
//                public void run() {
//                    new QuickHttp().url("http://127.0.0.1:8040/").addParame("imei",device.getImei()).addParame("data",message).post().text();
//                }
//            }).start();


        }
    }



}
