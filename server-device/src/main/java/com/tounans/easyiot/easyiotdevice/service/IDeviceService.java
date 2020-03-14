package com.tounans.easyiot.easyiotdevice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tounans.easyiot.common.entity.device.Device;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 格子
 * @since 2019-12-28
 */
public interface IDeviceService extends IService<Device> {

    Boolean saveOrUpdateUserDevice(Integer userId, Device device);

    Device getByUserAndUserDeviceId(Integer userId, Integer userDeviceId);

    Device getByUserAndImei(Integer userId, String imei);

    IPage<Device> pageByUserParam(Integer page,Integer pageSize, String imei, Integer userId);

    Integer countByImei(String imei);

    List<Device> getListByUserId(Integer userId);

    Device getDeviceByImei(String imei);
}
