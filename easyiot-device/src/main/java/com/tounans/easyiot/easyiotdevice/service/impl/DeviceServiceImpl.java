package com.tounans.easyiot.easyiotdevice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tounans.easyiot.easyiotdevice.entity.Device;
import com.tounans.easyiot.easyiotdevice.mapper.DeviceMapper;
import com.tounans.easyiot.easyiotdevice.service.IDeviceService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {


    private Integer getLastUserDeviceId(Integer userId){
        Integer result = baseMapper.getLastUserDeviceId(userId);
        return result==null?1:result;
    }

    private int pageSize = 20;

    @Override
    public boolean saveOrUpdateUserDevice(Integer userId, Device device) {
        device.setUserId(userId);

        if (device.getUserDeviceId() != null){
            Device byUserAndUserDeviceId = this.getByUserAndUserDeviceId(userId, device.getUserDeviceId());
            device.setId(byUserAndUserDeviceId.getId());
        }

        if (device.getUserDeviceId() == null){
            Integer lastUserDeviceId = this.getLastUserDeviceId(userId);
            device.setUserDeviceId(lastUserDeviceId);
            device.setAddTime(LocalDateTime.now());
        }

        return saveOrUpdate(device);
    }

    @Override
    public Device getDeviceByImei(String imei) {
        return getOne(new QueryWrapper<Device>().lambda().eq(Device::getImei,imei));
    }

    @Override
    public Device getByUserAndUserDeviceId(Integer userId, Integer userDeviceId) {
        return baseMapper.selectOne(new QueryWrapper<Device>().lambda().eq(Device::getUserId,userId).eq(Device::getUserDeviceId,userDeviceId));
    }

    @Override
    public IPage<Device> pageByUserParam(Integer page, String imei, Integer userId) {
        Page<Device> devicePage = new Page<Device>().setCurrent(page).setSize(pageSize).addOrder(OrderItem.asc("user_device_id"));
        QueryWrapper<Device> deviceQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(imei)){
            deviceQueryWrapper.lambda().eq(Device::getImei,imei);
        }

        if(userId != null){
            deviceQueryWrapper.lambda().eq(Device::getUserId,userId);
        }

        return page(devicePage,deviceQueryWrapper);
    }

    @Override
    public Integer countByImei(String imei) {
        return baseMapper.selectCount(new QueryWrapper<Device>().lambda().eq(Device::getImei,imei));
    }

    @Override
    public List<Device> getListByUserId(Integer userId) {
        return this.list(new QueryWrapper<Device>().lambda().eq(Device::getUserId,userId));
    }
}
