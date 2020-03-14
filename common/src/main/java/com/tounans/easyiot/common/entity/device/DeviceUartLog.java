package com.tounans.easyiot.common.entity.device;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 格子
 * @since 2019-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("iot_device_uart_log")
public class DeviceUartLog implements Serializable {


    private static final long serialVersionUID = 3030086009863963523L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 设备ID
     */
    private Integer userDeviceId;


    private Integer userId;

    private Integer userLogId;

    /**
     * Uart
     */
    private Integer userUartId;

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
