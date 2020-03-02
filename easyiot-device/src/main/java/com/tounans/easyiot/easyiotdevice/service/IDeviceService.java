package com.tounans.easyiot.easyiotdevice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tounans.easyiot.easyiotdevice.entity.Device;

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

    boolean saveOrUpdateUserDevice(Integer userId, Device device);

    Device getDeviceByImei(String imei);

    Device getByUserAndUserDeviceId(Integer userId, Integer userDeviceId);

    IPage<Device> pageByUserParam(Integer page, String imei, Integer userId);

    Integer countByImei(String imei);

    List<Device> getListByUserId(Integer userId);
}
