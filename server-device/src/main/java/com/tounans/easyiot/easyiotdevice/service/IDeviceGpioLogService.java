package com.tounans.easyiot.easyiotdevice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tounans.easyiot.common.entity.device.DeviceGpioLog;
import com.tounans.easyiot.common.view.device.DeviceGpioLogView;

/**
 * <p>
 *  服务类
 * </p>
 *
 *
 * @author 格子
 * @since 2019-12-28
 */
public interface IDeviceGpioLogService extends IService<DeviceGpioLog> {


    /**
     * 添加日志
     * @param deviceId
     * @param userId
     * @param gpioId
     * @param type 0上传 1下发
     * @param state
     * @return
     */
    boolean addLog(Integer deviceId,Integer userId, Integer gpioId,Integer type,Integer state);

    IPage<DeviceGpioLogView> pageByUserParam(Integer page, Integer pageSize, Integer userGpioId, Integer userId);
}
