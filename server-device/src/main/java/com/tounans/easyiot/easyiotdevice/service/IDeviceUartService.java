package com.tounans.easyiot.easyiotdevice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tounans.easyiot.common.entity.device.DeviceUart;
import com.tounans.easyiot.common.view.device.DeviceUartView;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 格子
 * @since 2019-12-28
 */
public interface IDeviceUartService extends IService<DeviceUart> {

    IPage<DeviceUartView> pageByUserParam(Integer page, Integer pageSize, Integer userDeviceId, Integer userId);

    DeviceUartView getByUserAndUserUartId(Integer userId, Integer userUartId);

    boolean saveOrUpdateUserDeviceUart(Integer userId, DeviceUart deviceUart);

    DeviceUart getByUserDeviceIdAndUartId(Integer userDeviceId, Integer uartId);

    List<DeviceUart> getListByUserId(Integer userId);
}
