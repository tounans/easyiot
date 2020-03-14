package com.tounans.easyiot.easyiotdevice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tounans.easyiot.common.entity.device.DeviceGpio;
import com.tounans.easyiot.common.view.device.DeviceGpioView;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 格子
 * @since 2019-12-28
 */
public interface IDeviceGpioService extends IService<DeviceGpio> {

    DeviceGpio getByUserDeviceIdAndGpioId(Integer userDeviceId, Integer gpioId);

    boolean updateCurrent(Integer userDeviceId, Integer userId, Integer gpio, Integer state);

    IPage<DeviceGpioView> pageByUserParam(Integer page, Integer pageSize, Integer userDeviceId, Integer userId);

    DeviceGpioView getByUserAndUserGpioId(Integer userId, Integer userGpioId);

    boolean saveOrUpdateUserDeviceGpio(Integer userId, DeviceGpio deviceGpio);

    List<DeviceGpio> listByUserIdAndDeviceIdAndState(Integer userId, Integer userDeviceId, boolean state);

    List<DeviceGpio> getListByUserId(Integer userId);
}
