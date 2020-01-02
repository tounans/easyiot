package com.tounans.easyiot.device.view;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class DeviceGpioLogView {

    private String imei;

    private Integer userLogId;

    /**
     * 别名
     */
    private String alias;

    /**
     * GPIO
     */
    private Integer gpioId;

    /**
     * 类型   0上报 1下发
     */
    private Integer type;

    /**
     * 状态
     */
    private Integer state;


    private LocalDateTime addTime;
}
