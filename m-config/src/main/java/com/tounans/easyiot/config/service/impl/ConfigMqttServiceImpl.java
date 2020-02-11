package com.tounans.easyiot.config.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tounans.easyiot.config.entity.ConfigMqtt;
import com.tounans.easyiot.config.mapper.ConfigMqttMapper;
import com.tounans.easyiot.config.service.IConfigMqttService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 格子
 * @since 2020-02-09
 */
@Service
public class ConfigMqttServiceImpl extends ServiceImpl<ConfigMqttMapper, ConfigMqtt> implements IConfigMqttService {

    @Override
    public ConfigMqtt getByUserId(Integer userId) {
        ConfigMqtt configMqtt = this.getOne(new QueryWrapper<ConfigMqtt>().eq("user_id", userId));
        if (configMqtt == null){
            configMqtt = new ConfigMqtt();
            configMqtt.setUserId(userId);
            this.save(configMqtt);
        }
        return configMqtt;
    }
}
