package com.tounans.easyiot.easyiotdevice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tounans.easyiot.common.entity.device.DeviceGpioLog;
import com.tounans.easyiot.common.view.device.DeviceGpioLogView;
import com.tounans.easyiot.easyiotdevice.mapper.DeviceGpioLogMapper;
import com.tounans.easyiot.easyiotdevice.service.IDeviceGpioLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 格子
 * @since 2019-12-28
 */
@Service
public class DeviceGpioLogServiceImpl extends ServiceImpl<DeviceGpioLogMapper, DeviceGpioLog> implements IDeviceGpioLogService {

    private Integer getUserLogId(Integer userId){
        Integer result = baseMapper.getUserLogId(userId);
        return result==null?1:result;
    }

    public boolean addLog(Integer deviceId,Integer userId, Integer gpioId,Integer type,Integer state){
        Integer userLogId = this.getUserLogId(userId);
        DeviceGpioLog deviceGpioLog = new DeviceGpioLog().setUserDeviceId(deviceId).setUserLogId(userLogId).setUserId(userId).setUserGpioId(gpioId).setType(type).setState(state).setAddTime(LocalDateTime.now());
        return save(deviceGpioLog);
    }

    @Override
    public IPage<DeviceGpioLogView> pageByUserParam(Integer page, Integer pageSize, Integer userGpioId, Integer userId) {

        Page<DeviceGpioLogView> deviceGpioPage = new Page<DeviceGpioLogView>().setCurrent(page).setSize(pageSize).addOrder(OrderItem.desc("user_log_id"));
        QueryWrapper<DeviceGpioLogView> deviceGpioQueryWrapper = new QueryWrapper<>();

        if (userGpioId!=null){
            deviceGpioQueryWrapper.eq("iot_device_gpio.gpio_id",userGpioId);
        }

        if(userId!=null){
            deviceGpioQueryWrapper.eq("iot_device_gpio_log.user_id",userId);
        }

        return baseMapper.selectPageByParam(deviceGpioPage,deviceGpioQueryWrapper);
    }
}
