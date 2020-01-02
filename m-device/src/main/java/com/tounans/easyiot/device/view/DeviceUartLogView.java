package com.tounans.easyiot.device.view;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class DeviceUartLogView {
    private String imei;

    private Integer userLogId;
    /**
     * GPIO
     */
    private Integer uartId;

    /**
     * 别名
     */
    private String alias;

    /**
     * 类型   0上报 1下发
     */
    private Integer type;

    /**
     * 状态
     */
    private String data;


    private LocalDateTime addTime;
}
