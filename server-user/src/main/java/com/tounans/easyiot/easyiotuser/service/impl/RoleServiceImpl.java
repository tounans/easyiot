package com.tounans.easyiot.easyiotuser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tounans.easyiot.common.entity.user.Role;
import com.tounans.easyiot.easyiotuser.mapper.RoleMapper;
import com.tounans.easyiot.easyiotuser.service.IRoleService;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author 格子
 * @since 2020-03-05
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
