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
@TableName("iot_device_gpio")
public class DeviceGpio implements Serializable {

    private static final long serialVersionUID = 4788633742247909466L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 设备ID
     */
    private Integer deviceId;

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
