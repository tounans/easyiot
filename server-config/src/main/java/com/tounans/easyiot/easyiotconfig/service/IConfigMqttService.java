package com.tounans.easyiot.easyiotconfig.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tounans.easyiot.common.entity.config.ConfigMqtt;

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

    boolean editAndSaveConfigMqtt(Integer userId, ConfigMqtt configMqtt);
}
