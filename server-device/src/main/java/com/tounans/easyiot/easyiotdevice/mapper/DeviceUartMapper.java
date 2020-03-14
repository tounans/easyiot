package com.tounans.easyiot.easyiotdevice.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tounans.easyiot.common.entity.device.DeviceUart;
import com.tounans.easyiot.common.view.device.DeviceUartView;
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
public interface DeviceUartMapper extends BaseMapper<DeviceUart> {

    @Select("SELECT\n" +
            "\tiot_device_uart.id,\n" +
            "\tiot_device_uart.device_id,\n" +
            "\tiot_device_uart.user_id,\n" +
            "\tiot_device_uart.uart_id,\n" +
            "\tiot_device_uart.user_uart_id,\n" +
            "\tiot_device_uart.method,\n" +
            "\tiot_device_uart.baud,\n" +
            "\tiot_device_uart.add_time,\n" +
            "\tiot_device_uart.databits,\n" +
            "\tiot_device_uart.parity,\n" +
            "\tiot_device_uart.stopbits,\n" +
            "\tiot_device_uart.state,\n" +
            "\tiot_device.alias, \n" +
            "\tiot_device.imei \n" +
            "FROM\n" +
            "\tiot_device_uart\n" +
            "\tINNER JOIN iot_device ON iot_device_uart.device_id = iot_device.user_device_id \n" +
            "\tAND iot_device_uart.user_id = iot_device.user_id \n" +
            "WHERE ${ew.sqlSegment}")
    IPage<DeviceUartView> selectPageByParam(Page<DeviceUartView> page, @Param("ew") QueryWrapper<DeviceUartView> queryWrapper);

    @Select("SELECT\n" +
            "\tiot_device_uart.id,\n" +
            "\tiot_device_uart.device_id,\n" +
            "\tiot_device_uart.user_id,\n" +
            "\tiot_device_uart.uart_id,\n" +
            "\tiot_device_uart.user_uart_id,\n" +
            "\tiot_device_uart.method,\n" +
            "\tiot_device_uart.baud,\n" +
            "\tiot_device_uart.add_time,\n" +
            "\tiot_device_uart.databits,\n" +
            "\tiot_device_uart.parity,\n" +
            "\tiot_device_uart.stopbits,\n" +
            "\tiot_device_uart.state,\n" +
            "\tiot_device.alias, \n" +
            "\tiot_device.imei \n" +
            "FROM\n" +
            "\tiot_device_uart\n" +
            "\tINNER JOIN iot_device ON iot_device_uart.device_id = iot_device.user_device_id \n" +
            "\tAND iot_device_uart.user_id = iot_device.user_id \n" +
            "WHERE  ${ew.sqlSegment}")
    DeviceUartView selectOneByParam(@Param("ew") QueryWrapper<DeviceUartView> eq);

    @Select("select user_uart_id+1 from iot_device_uart where user_id = #{userId}  order by user_uart_id desc limit 1")
    Integer getLastUserDeviceUartId(int userId);
}
