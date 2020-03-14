package com.tounans.easyiot.easyiotdevice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tounans.easyiot.common.entity.device.DeviceUartLog;
import com.tounans.easyiot.common.view.device.DeviceUartLogView;
import com.tounans.easyiot.easyiotdevice.mapper.DeviceUartLogMapper;
import com.tounans.easyiot.easyiotdevice.service.IDeviceUartLogService;
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
public class DeviceUartLogServiceImpl extends ServiceImpl<DeviceUartLogMapper, DeviceUartLog> implements IDeviceUartLogService {

    private Integer getUserLogId(Integer userId){
        Integer result = baseMapper.getUserLogId(userId);
        return result==null?1:result;
    }
    @Override
    public boolean addLog(Integer deviceId,Integer userId, Integer uartId,Integer type,String data){

        Integer userLogId = this.getUserLogId(userId);
        DeviceUartLog deviceUartLog = new DeviceUartLog().setUserDeviceId(deviceId).setUserLogId(userLogId).setUserId(userId).setUserUartId(uartId).setType(type).setData(data).setAddTime(LocalDateTime.now());
        return save(deviceUartLog);
    }


    @Override
    public IPage<DeviceUartLogView> pageByUserParam(Integer page,Integer pageSize, Integer userUartId, Integer userId) {
        Page<DeviceUartLogView> deviceGpioPage = new Page<DeviceUartLogView>().setCurrent(page).setSize(pageSize).addOrder(OrderItem.desc("user_log_id"));
        QueryWrapper<DeviceUartLogView> deviceGpioQueryWrapper = new QueryWrapper<>();

        if (userUartId!=null){
            deviceGpioQueryWrapper.eq("iot_device_uart.uart_id",userUartId);
        }

        if(userId!=null){
            deviceGpioQueryWrapper.eq("iot_device_uart_log.user_id",userId);
        }

        return baseMapper.selectPageByParam(deviceGpioPage,deviceGpioQueryWrapper);
    }

}
