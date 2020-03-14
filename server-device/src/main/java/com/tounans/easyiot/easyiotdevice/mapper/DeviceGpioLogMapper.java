package com.tounans.easyiot.easyiotdevice.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tounans.easyiot.common.entity.device.DeviceGpioLog;
import com.tounans.easyiot.common.view.device.DeviceGpioLogView;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 格子
 * @since 2019-12-28
 */
public interface DeviceGpioLogMapper extends BaseMapper<DeviceGpioLog> {

    @Select("select user_log_id+1 from iot_device_gpio_log where user_id = #{userId} order by user_log_id desc limit 1")
    Integer getUserLogId(Integer userId);


    @Select("SELECT\n" +
            "iot_device_gpio_log.user_log_id,\n" +
            "iot_device_gpio_log.type,\n" +
            "iot_device_gpio_log.state,\n" +
            "iot_device_gpio_log.add_time,\n" +
            "iot_device_gpio.gpio_id,\n" +
            "iot_device.alias,\n" +
            "iot_device.imei\n" +
            "FROM\n" +
            "iot_device_gpio_log\n" +
            "LEFT JOIN iot_device_gpio ON iot_device_gpio_log.user_id = iot_device_gpio.user_id AND iot_device_gpio_log.user_gpio_id = iot_device_gpio.user_gpio_id AND iot_device_gpio_log.user_device_id = iot_device_gpio.device_id\n" +
            "LEFT JOIN iot_device ON iot_device_gpio.user_id = iot_device.user_id AND iot_device_gpio.device_id = iot_device.user_device_id \n" +
            "WHERE ${ew.sqlSegment} ")
    IPage<DeviceGpioLogView> selectPageByParam(IPage<DeviceGpioLogView> page, @Param("ew") Wrapper<DeviceGpioLogView> queryWrapper);


}
