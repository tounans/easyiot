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
@TableName("iot_device_uart")
public class DeviceUart implements Serializable {


    private static final long serialVersionUID = -6035288720013828200L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer deviceId;


    private Integer uartId;

    /**
     * UserId
     */
    private Integer userId;

    /**
     * 用户UartID
     */
    private Integer userUartId;

    /**
     * --1读  0写
     */
    private Integer method;

    /**
     * 波特率
     */
    private Integer baud;

    /**
     * 数据位
     */
    private Integer databits;

    /**
     * 校验位
     */
    private Integer parity;

    /**
     * 停止位
     */
    private Integer stopbits;

    private Boolean state;


    private LocalDateTime addTime;


}
