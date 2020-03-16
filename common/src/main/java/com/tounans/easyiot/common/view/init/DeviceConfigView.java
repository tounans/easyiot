package com.tounans.easyiot.common.view.init;

import com.tounans.easyiot.common.entity.device.DeviceGpio;
import com.tounans.easyiot.common.entity.device.DeviceUart;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class DeviceConfigView {

    List<Map<String,Object>> pinsConfig;

    List<Map<String,Object>> uartConfig;

    public DeviceConfigView() {
        pinsConfig = new ArrayList<>();
        uartConfig = new ArrayList<>();
    }

    public DeviceConfigView(List<DeviceGpio> deviceGpioList, List<DeviceUart> deviceUartList) {
        pinsConfig = new ArrayList<>();
        uartConfig = new ArrayList<>();
        this.putPinsConfig(deviceGpioList);
        this.putUartConfig(deviceUartList);
    }

    public DeviceConfigView putPinsConfig(List<DeviceGpio> deviceGpioList){
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

    public DeviceConfigView putUartConfig(List<DeviceUart> deviceUartList){
        List<Map<String,Object>> tempList = new ArrayList<>();
        for (DeviceUart deviceUart : deviceUartList){
            Map<String,Object> tempMap = new HashMap<>();
            tempMap.put("method",deviceUart.getMethod());
            tempMap.put("id",deviceUart.getUartId());
            tempMap.put("baud",deviceUart.getBaud());
            tempMap.put("databits",deviceUart.getDatabits());
            tempMap.put("parity",deviceUart.getParity());
            if (deviceUart.getStopbits() == 1){
                tempMap.put("stopbits",2);
            }else{
                tempMap.put("stopbits",deviceUart.getStopbits());
            }
            tempList.add(tempMap);

        }
        uartConfig.addAll(tempList);
        return this;
    }
}
