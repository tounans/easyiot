package com.tounans.easyiot.easyiotdevice.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tounans.easyiot.common.entity.device.DeviceGpio;
import com.tounans.easyiot.common.view.device.DeviceGpioView;
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
public interface DeviceGpioMapper extends BaseMapper<DeviceGpio> {

    @Select("SELECT\n" +
            "\tiot_device_gpio.id,\n" +
            "\tiot_device.imei,\n" +
            "\tiot_device.alias,\n" +
            "\tiot_device_gpio.device_id,\n" +
            "\tiot_device_gpio.user_gpio_id,\n" +
            "\tiot_device_gpio.user_id,\n" +
            "\tiot_device_gpio.gpio_id,\n" +
            "\tiot_device_gpio.method,\n" +
            "\tiot_device_gpio.def,\n" +
            "\tiot_device_gpio.current,\n" +
            "\tiot_device_gpio.add_time,\n" +
            "\tiot_device_gpio.state \n" +
            "FROM\n" +
            "\tiot_device_gpio\n" +
            "\tLEFT JOIN iot_device ON iot_device_gpio.device_id = iot_device.user_device_id \n" +
            "\tAND iot_device_gpio.user_id = iot_device.user_id \n"+
            "WHERE ${ew.sqlSegment} ")
    IPage<DeviceGpioView> selectPageByParam(IPage<DeviceGpioView> page, @Param("ew") Wrapper<DeviceGpioView> queryWrapper);

    @Select("SELECT\n" +
            "\tiot_device_gpio.id,\n" +
            "\tiot_device.imei,\n" +
            "\tiot_device.alias,\n" +
            "\tiot_device_gpio.device_id,\n" +
            "\tiot_device_gpio.user_gpio_id,\n" +
            "\tiot_device_gpio.user_id,\n" +
            "\tiot_device_gpio.gpio_id,\n" +
            "\tiot_device_gpio.method,\n" +
            "\tiot_device_gpio.def,\n" +
            "\tiot_device_gpio.current,\n" +
            "\tiot_device_gpio.add_time,\n" +
            "\tiot_device_gpio.state \n" +
            "FROM\n" +
            "\tiot_device_gpio\n" +
            "\tLEFT JOIN iot_device ON iot_device_gpio.device_id = iot_device.user_device_id \n" +
            "\tAND iot_device_gpio.user_id = iot_device.user_id \n" +
            "WHERE  ${ew.sqlSegment} ")
    DeviceGpioView selectOneByParam(@Param("ew") QueryWrapper<DeviceGpioView> deviceGpioQueryWrapper);


    @Select("select user_gpio_id+1 from iot_device_gpio where user_id = #{userId} order by user_gpio_id desc limit 1")
    Integer getLastUserDeviceGpioId(Integer userId);
}
