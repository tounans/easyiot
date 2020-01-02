package com.tounans.easyiot.device.view;

import com.tounans.easyiot.device.entity.DeviceGpio;
import com.tounans.easyiot.device.entity.DeviceUart;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class DeviceConfig {

    List<Map<String,Object>> pinsConfig;

    List<Map<String,Object>> uartConfig;

    public DeviceConfig() {
        pinsConfig = new ArrayList<>();
        uartConfig = new ArrayList<>();
    }

    public DeviceConfig(List<DeviceGpio> deviceGpioList,List<DeviceUart> deviceUartList) {
        pinsConfig = new ArrayList<>();
        uartConfig = new ArrayList<>();
        this.putPinsConfig(deviceGpioList);
        this.putUartConfig(deviceUartList);
    }

    public DeviceConfig putPinsConfig(List<DeviceGpio> deviceGpioList){
        List<Map<String,Object>> tempList = new ArrayList<>();
        for (DeviceGpio deviceGpio :deviceGpioList ){
            Map<String,Object> tempMap = new HashMap<>();
            tempMap.put("gpio",deviceGpio.getGpioId());
            tempMap.put("method",deviceGpio.getMethod());
            tempMap.put("default",deviceGpio.getDef());
            tempList.add(tempMap);
        }
        pinsConfig.addAll(tempList);

        return this;
    }

    public DeviceConfig putUartConfig(List<DeviceUart> deviceUartList){
        List<Map<String,Object>> tempList = new ArrayList<>();
        for (DeviceUart deviceUart : deviceUartList){
            Map<String,Object> tempMap = new HashMap<>();
            tempMap.put("method",deviceUart.getMethod());
            tempMap.put("id",deviceUart.getUartId());
            tempMap.put("baud",deviceUart.getBaud());
            tempMap.put("databits",deviceUart.getDatabits());
            tempMap.put("parity",deviceUart.getParity());
            tempMap.put("stopbits",deviceUart.getStopbits());
            tempList.add(tempMap);

        }
        uartConfig.addAll(tempList);
        return this;
    }
}
