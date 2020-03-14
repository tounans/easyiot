package com.tounans.easyiot.common.view.device;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeviceGpioView {
    private Integer id;

    /**
     * 设备ID
     */
    private String imei;

    /**
     * 设备ID
     */
    private Integer deviceId;
    /**
     * 别名
     */
    private String alias;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户管脚ID
     */
    private Integer userGpioId;

    /**
     * GPIO
     */
    private Integer gpioId;

    /**
     * --1输出 0中断
     */
    private Integer method;

    /**
     * 默认电平
     */
    private Integer def;

    /**
     * 当前电平
     */
    private Integer current;

    private Boolean state;

    private LocalDateTime addTime;
}
