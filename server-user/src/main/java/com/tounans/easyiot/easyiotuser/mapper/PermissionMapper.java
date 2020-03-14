package com.tounans.easyiot.easyiotuser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tounans.easyiot.common.entity.user.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author 格子
 * @since 2020-03-05
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("SELECT\n" +
            "p.id,\n" +
            "p.parent_id,\n" +
            "p.`name`,\n" +
            "p.enname,\n" +
            "p.url,\n" +
            "p.description,\n" +
            "p.created,\n" +
            "p.updated\n" +
            "FROM\n" +
            "iot_user AS u\n" +
            "LEFT JOIN iot_user_role AS ur ON u.id = ur.user_id\n" +
            "LEFT JOIN iot_role AS r ON r.id = ur.role_id\n" +
            "LEFT JOIN iot_role_permission AS rp ON r.id = rp.role_id\n" +
            "LEFT JOIN iot_permission AS p ON p.id = rp.permission_id\n" +
            "WHERE\n" +
            "u.id = #{userId}")
    List<Permission> getByUserId(Integer userId);

}
