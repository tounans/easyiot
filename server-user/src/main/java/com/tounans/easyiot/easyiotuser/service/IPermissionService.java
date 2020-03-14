package com.tounans.easyiot.easyiotuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tounans.easyiot.common.entity.user.Permission;

import java.util.List;


/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author 格子
 * @since 2020-03-05
 */
public interface IPermissionService extends IService<Permission> {
    List<Permission> getByUserId(Integer userId);
}
