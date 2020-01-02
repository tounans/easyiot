package com.tounans.easyiot.device.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tounans.easyiot.device.entity.DeviceUart;
import com.tounans.easyiot.device.entity.DeviceUartLog;
import com.tounans.easyiot.device.view.DeviceUartLogView;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 格子
 * @since 2019-12-28
 */
public interface IDeviceUartLogService extends IService<DeviceUartLog> {

    /**
     * 添加上传日志
     */
    boolean addUpLog(Integer deviceId,Integer userId, Integer uartId,  String data);

    /**
     * 添加下发日志
     */

    boolean addDowLog(Integer deviceId,Integer userId, Integer uartId,  String data);


    IPage<DeviceUartLogView> pageByUserParam(int page, Integer userUartId, Integer userId);
}
