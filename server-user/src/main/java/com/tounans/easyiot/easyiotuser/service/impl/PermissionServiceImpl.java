package com.tounans.easyiot.easyiotuser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tounans.easyiot.common.entity.user.Permission;
import com.tounans.easyiot.easyiotuser.mapper.PermissionMapper;
import com.tounans.easyiot.easyiotuser.service.IPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author 格子
 * @since 2020-03-05
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<Permission> getByUserId(Integer userId) {
        return baseMapper.getByUserId(userId);
    }
}
