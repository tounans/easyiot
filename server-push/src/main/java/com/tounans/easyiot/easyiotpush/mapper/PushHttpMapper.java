package com.tounans.easyiot.easyiotpush.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tounans.easyiot.common.entity.push.PushHttp;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 格子
 * @since 2020-01-04
 */
public interface PushHttpMapper extends BaseMapper<PushHttp> {

    @Select("select user_push_id+1 from iot_push_http where user_id = #{userId} order by user_push_id desc limit 1")
    Integer getLastUserPushId(Integer userId);
}
