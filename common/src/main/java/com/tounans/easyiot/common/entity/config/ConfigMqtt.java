package com.tounans.easyiot.common.entity.config;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 格子
 * @since 2020-02-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("iot_config_mqtt")
public class ConfigMqtt implements Serializable {


    private static final long serialVersionUID = -3903048997559502872L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Boolean isDef;

    private String host;

    private String port;

    private String username;

    private String password;


}
