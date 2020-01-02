package com.tounans.easyiot.device.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tounans.easyiot.device.entity.DeviceGpio;
import com.tounans.easyiot.device.entity.DeviceGpioLog;
import com.tounans.easyiot.device.view.DeviceGpioLogView;

import java.util.List;

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
     * 添加上传日志
     */
    boolean addUpLog(Integer deviceId,Integer userId, Integer gpioId, Integer state);

    /**
     * 添加下发日志
     */

    boolean addDowLog(Integer deviceId,Integer userId, Integer gpioId, Integer state);

    IPage<DeviceGpioLogView> pageByUserParam(int page, Integer userGpioId, Integer userId);
}
