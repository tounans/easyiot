package com.tounans.easyiot.config.service;

import com.tounans.easyiot.config.entity.ConfigMqtt;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 格子
 * @since 2020-02-09
 */
public interface IConfigMqttService extends IService<ConfigMqtt> {

    ConfigMqtt getByUserId(Integer userId);

}
