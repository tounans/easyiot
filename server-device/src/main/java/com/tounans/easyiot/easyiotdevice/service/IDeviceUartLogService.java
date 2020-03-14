package com.tounans.easyiot.easyiotdevice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tounans.easyiot.common.entity.device.DeviceUartLog;
import com.tounans.easyiot.common.view.device.DeviceUartLogView;

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
     *
     * @param deviceId
     * @param userId
     * @param uartId
     * @param type 0上传 1下发
     * @param data
     * @return
     */
    boolean addLog(Integer deviceId,Integer userId, Integer uartId,Integer type,String data);


    IPage<DeviceUartLogView> pageByUserParam(Integer page, Integer pageSize, Integer userUartId, Integer userId);
}
