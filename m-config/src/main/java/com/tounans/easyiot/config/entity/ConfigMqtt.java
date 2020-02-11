package com.tounans.easyiot.config.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2020-02-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("iot_config_mqtt")
public class ConfigMqtt implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Boolean isDef;

    private String host;

    private String port;

    private String username;

    private String password;


}
