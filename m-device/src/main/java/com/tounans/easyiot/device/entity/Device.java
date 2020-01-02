package com.tounans.easyiot.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("iot_device")
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户设备ID
     */
    private Integer userDeviceId;

    private String imei;

    private String model;

    private LocalDateTime addTime;

    private Integer userId;

    /**
     * 别名
     */
    private String alias;

    /**
     * 备注
     */
    private String remark;


    /**
     * 版本号
     */
    private String version;

    /**
     * 固件名称
     */
    private String firmwareName;


    /**
     * 基站定位信息
     */

    private String lbs;

    /**
     * 信号强度
     */

    private Integer networkSignal;


    private Boolean state;

}
