package com.tounans.easyiot.easyiotdevice.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tounans.easyiot.common.entity.device.DeviceUartLog;
import com.tounans.easyiot.common.view.device.DeviceUartLogView;
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
public interface DeviceUartLogMapper extends BaseMapper<DeviceUartLog> {

    @Select("select user_log_id+1 from iot_device_uart_log where user_id = #{userId} order by user_log_id desc limit 1")
    Integer getUserLogId(Integer userId);

    @Select("SELECT\n" +
            "\tiot_device_uart_log.type,\n" +
            "\tiot_device_uart_log.`data`,\n" +
            "\tiot_device_uart_log.add_time,\n" +
            "\tiot_device_uart.uart_id,\n" +
            "\tiot_device.alias,\n" +
            "\tiot_device.imei,\n" +
            "\tiot_device_uart_log.user_log_id \n" +
            "FROM\n" +
            "\tiot_device_uart_log\n" +
            "\tLEFT JOIN iot_device_uart ON iot_device_uart_log.user_device_id = iot_device_uart.device_id \n" +
            "\tAND iot_device_uart_log.user_uart_id = iot_device_uart.user_uart_id \n" +
            "\tAND iot_device_uart_log.user_id = iot_device_uart.user_id\n" +
            "\tINNER JOIN iot_device ON iot_device_uart.device_id = iot_device.user_device_id \n" +
            "WHERE ${ew.sqlSegment} ")
    IPage<DeviceUartLogView> selectPageByParam(IPage<DeviceUartLogView> page, @Param("ew") Wrapper<DeviceUartLogView> queryWrapper);
}
