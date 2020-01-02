package com.tounans.easyiot.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tounans.easyiot.device.entity.DeviceGpioLog;
import com.tounans.easyiot.device.mapper.DeviceGpioLogMapper;
import com.tounans.easyiot.device.service.IDeviceGpioLogService;
import com.tounans.easyiot.device.view.DeviceGpioLogView;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    private int pageSize = 20;


    private Integer getUserLogId(Integer userId){
        Integer result = baseMapper.getUserLogId(userId);
        return result==null?1:result;
    }

    private boolean addLog(Integer deviceId,Integer userId, Integer gpioId,Integer type,Integer state){

        Integer userLogId = this.getUserLogId(userId);
        DeviceGpioLog deviceGpioLog = new DeviceGpioLog().setDeviceId(deviceId).setUserLogId(userLogId).setUserId(userId).setGpioId(gpioId).setType(type).setState(state).setAddTime(LocalDateTime.now());
        return save(deviceGpioLog);
    }

    @Override
    public boolean addUpLog(Integer deviceId,Integer userId, Integer gpioId, Integer state) {
        return this.addLog(deviceId,userId,gpioId,0,state);
    }

    @Override
    public boolean addDowLog(Integer deviceId,Integer userId, Integer gpioId, Integer state) {
        return this.addLog(deviceId,userId,gpioId,1,state);
    }

    @Override
    public IPage<DeviceGpioLogView> pageByUserParam(int page, Integer userGpioId, Integer userId) {

        Page<DeviceGpioLogView> deviceGpioPage = new Page<DeviceGpioLogView>().setCurrent(page).setSize(pageSize).addOrder(OrderItem.desc("user_log_id"));
        QueryWrapper<DeviceGpioLogView> deviceGpioQueryWrapper = new QueryWrapper<>();

        if (userGpioId!=null){
            deviceGpioQueryWrapper.eq("iot_device_gpio.user_gpio_id",userGpioId);
        }

        if(userId!=null){
            deviceGpioQueryWrapper.eq("iot_device_gpio_log.user_id",userId);
        }

        return baseMapper.selectPageByParam(deviceGpioPage,deviceGpioQueryWrapper);
    }
}
