package com.tounans.easyiot.easyiotconfig.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tounans.easyiot.common.entity.config.ConfigMqtt;
import com.tounans.easyiot.easyiotconfig.mapper.ConfigMqttMapper;
import com.tounans.easyiot.easyiotconfig.service.IConfigMqttService;
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

    @Override
    public boolean editAndSaveConfigMqtt(Integer userId, ConfigMqtt configMqtt) {
        ConfigMqtt byUserId = getByUserId(userId);
        ConfigMqtt configMqtt1 = configMqtt.setUserId(userId).setId(byUserId.getId());
        return this.updateById(configMqtt1);
    }
}
