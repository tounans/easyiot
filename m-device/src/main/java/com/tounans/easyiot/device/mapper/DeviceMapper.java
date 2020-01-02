package com.tounans.easyiot.device.mapper;
import com.tounans.easyiot.device.entity.Device;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 格子
 * @since 2019-12-28
 */
public interface DeviceMapper extends BaseMapper<Device> {

    @Select("select user_device_id+1 from iot_device where user_id = #{userId} order by user_device_id desc limit 1")
    Integer getLastUserDeviceId(Integer userId);
}
