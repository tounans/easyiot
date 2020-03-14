package com.tounans.easyiot.easyiotuser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tounans.easyiot.common.entity.user.UserRole;
import com.tounans.easyiot.easyiotuser.mapper.UserRoleMapper;
import com.tounans.easyiot.easyiotuser.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author 格子
 * @since 2020-03-05
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
