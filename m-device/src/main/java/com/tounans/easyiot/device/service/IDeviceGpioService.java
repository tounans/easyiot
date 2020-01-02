package com.tounans.easyiot.device.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tounans.easyiot.device.entity.DeviceGpio;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tounans.easyiot.device.entity.DeviceUart;
import com.tounans.easyiot.device.view.DeviceGpioView;

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

    DeviceGpio getByUserDeviceIdAndGpioId(Integer userDeviceId,Integer gpioId);

    boolean updateCurrent(Integer userDeviceId,Integer userId, Integer gpio, Integer state);

    IPage<DeviceGpioView> pageByUserParam(int page, Integer userDeviceId, Integer userId);

    DeviceGpioView getByUserAndUserGpioId(Integer userId, Integer userGpioId);

    boolean saveOrUpdateUserDeviceGpio(int userId, DeviceGpio deviceGpio);


    List<DeviceGpio> listByUserIdAndDeviceIdAndState(Integer userId, Integer userDeviceId,boolean state);
}
