package com.tounans.easyiot.easyiotdevice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tounans.easyiot.common.entity.device.DeviceGpio;
import com.tounans.easyiot.common.view.device.DeviceGpioView;
import com.tounans.easyiot.easyiotdevice.mapper.DeviceGpioMapper;
import com.tounans.easyiot.easyiotdevice.service.IDeviceGpioService;
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
public class DeviceGpioServiceImpl extends ServiceImpl<DeviceGpioMapper, DeviceGpio> implements IDeviceGpioService {


    private Integer getLastUserDeviceGpioId(Integer userId){
        Integer result = baseMapper.getLastUserDeviceGpioId(userId);
        return result==null?1:result;
    }

    @Override
    public DeviceGpio getByUserDeviceIdAndGpioId(Integer userDeviceId, Integer gpioId) {
        return this.getOne(new QueryWrapper<DeviceGpio>().lambda().eq(DeviceGpio::getGpioId,gpioId).eq(DeviceGpio::getDeviceId,userDeviceId));
    }

    @Override
    public boolean updateCurrent(Integer userDeviceId,Integer userId, Integer gpio, Integer state) {
        return update(new DeviceGpio().setCurrent(state),new UpdateWrapper<DeviceGpio>().eq("device_id",userDeviceId).eq("user_id",userId).eq("gpio_id",gpio));
    }


    @Override
    public IPage<DeviceGpioView> pageByUserParam(Integer page, Integer pageSize, Integer userDeviceId, Integer userId) {
        Page<DeviceGpioView> deviceGpioPage = new Page<DeviceGpioView>().setCurrent(page).setSize(pageSize).addOrder(OrderItem.asc("user_gpio_id"));
        QueryWrapper<DeviceGpioView> deviceGpioQueryWrapper = new QueryWrapper<>();

        if (userDeviceId!=null){
            deviceGpioQueryWrapper.eq("iot_device_gpio.device_id",userDeviceId);
        }

        if(userId!=null){
            deviceGpioQueryWrapper.eq("iot_device_gpio.user_id",userId);
        }

        return baseMapper.selectPageByParam(deviceGpioPage,deviceGpioQueryWrapper);
    }

    @Override
    public DeviceGpioView getByUserAndUserGpioId(Integer userId, Integer userGpioId) {
        return baseMapper.selectOneByParam(new QueryWrapper<DeviceGpioView>().eq("iot_device_gpio.user_gpio_id",userGpioId).eq("iot_device_gpio.user_id",userId));
    }

    @Override
    public boolean saveOrUpdateUserDeviceGpio(Integer userId, DeviceGpio deviceGpio) {

        deviceGpio.setUserId(userId);

        if (deviceGpio.getUserGpioId() != null){
            DeviceGpioView byUserAndUserGpioId = this.getByUserAndUserGpioId(userId, deviceGpio.getUserGpioId());
            deviceGpio.setId(byUserAndUserGpioId.getId());
        }

        if (deviceGpio.getUserGpioId() == null){
            Integer lastUserDeviceId = this.getLastUserDeviceGpioId(userId);
            deviceGpio.setUserGpioId(lastUserDeviceId);
            deviceGpio.setAddTime(LocalDateTime.now());
        }

        return saveOrUpdate(deviceGpio);
    }

    @Override
    public List<DeviceGpio> listByUserIdAndDeviceIdAndState(Integer userId, Integer userDeviceId, boolean state) {
        return this.list(new QueryWrapper<DeviceGpio>().lambda().eq(DeviceGpio::getUserId,userId).eq(DeviceGpio::getDeviceId,userDeviceId).eq(DeviceGpio::getState,state));
    }

    @Override
    public List<DeviceGpio> getListByUserId(Integer userId) {
        return this.list(new QueryWrapper<DeviceGpio>().lambda().eq(DeviceGpio::getUserId,userId));
    }
}
