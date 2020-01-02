package com.tounans.easyiot.device.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tounans.easyiot.device.entity.DeviceUart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tounans.easyiot.device.view.DeviceUartView;

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

    IPage<DeviceUartView> pageByUserParam(int page, Integer userDeviceId, Integer userId);

    DeviceUartView getByUserAndUserUartId(int userId, Integer userUartId);

    boolean saveOrUpdateUserDeviceUart(int userId, DeviceUart deviceUart);

    /**
     * 获得读取的串口
     * @param userDeviceId
     * @param uartId
     * @return
     */
    DeviceUart getReadByUserDeviceIdAndUartId(Integer userDeviceId, Integer uartId);

    List<DeviceUart> listByUserIdAndDeviceIdAndState(Integer userId,Integer userDeviceId,boolean state);
}
