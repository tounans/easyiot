package com.tounans.easyiot.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tounans.easyiot.device.entity.DeviceUart;
import com.tounans.easyiot.device.mapper.DeviceUartMapper;
import com.tounans.easyiot.device.service.IDeviceUartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tounans.easyiot.device.view.DeviceGpioView;
import com.tounans.easyiot.device.view.DeviceUartView;
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
public class DeviceUartServiceImpl extends ServiceImpl<DeviceUartMapper, DeviceUart> implements IDeviceUartService {

    private Integer getLastUserDeviceUartId(Integer userId){
        Integer result = baseMapper.getLastUserDeviceUartId(userId);
        return result==null?1:result;
    }

    private int pageSize = 20;


    @Override
    public IPage<DeviceUartView> pageByUserParam(int page, Integer userDeviceId, Integer userId) {
        Page<DeviceUartView> deviceGpioPage = new Page<DeviceUartView>().setCurrent(page).setSize(pageSize).addOrder(OrderItem.asc("user_uart_id"));
        QueryWrapper<DeviceUartView> deviceUartViewQueryWrapper = new QueryWrapper<DeviceUartView>();

        if (userDeviceId != null){
            deviceUartViewQueryWrapper.eq("iot_device_uart.device_id",userDeviceId);
        }

        if(userId!=null){
            deviceUartViewQueryWrapper.eq("iot_device_uart.user_id",userId);
        }

        return baseMapper.selectPageByParam(deviceGpioPage,deviceUartViewQueryWrapper);
    }

    @Override
    public DeviceUartView getByUserAndUserUartId(int userId, Integer userUartId) {
        return baseMapper.selectOneByParam(new QueryWrapper<DeviceUartView>().eq("iot_device_uart.user_id",userId).eq("iot_device_uart.user_uart_id",userUartId));
    }

    @Override
    public boolean saveOrUpdateUserDeviceUart(int userId, DeviceUart deviceUart) {

        deviceUart.setUserId(userId);

        if (deviceUart.getUserUartId() != null){
            DeviceUartView byUserAndUserUartId = this.getByUserAndUserUartId(userId, deviceUart.getUserUartId());
            deviceUart.setId(byUserAndUserUartId.getId());
        }

        if (deviceUart.getUserUartId() == null){
            Integer lastUserDeviceId = this.getLastUserDeviceUartId(userId);
            deviceUart.setUserUartId(lastUserDeviceId);
            deviceUart.setAddTime(LocalDateTime.now());
        }

        return  saveOrUpdate(deviceUart);
    }

    @Override
    public DeviceUart getReadByUserDeviceIdAndUartId(Integer userDeviceId, Integer uartId) {
        return this.getOne(new QueryWrapper<DeviceUart>().lambda().eq(DeviceUart::getDeviceId,userDeviceId).eq(DeviceUart::getUartId,uartId).eq(DeviceUart::getMethod,0));
    }

    @Override
    public List<DeviceUart> listByUserIdAndDeviceIdAndState(Integer userId,Integer userDeviceId,boolean state) {
        return this.list(new QueryWrapper<DeviceUart>().lambda().eq(DeviceUart::getDeviceId,userDeviceId).eq(DeviceUart::getUserId,userId).eq(DeviceUart::getState,state));
    }
}
