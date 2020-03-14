package com.tounans.easyiot.easyiotuser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tounans.easyiot.common.entity.user.User;
import com.tounans.easyiot.easyiotuser.mapper.UserMapper;
import com.tounans.easyiot.easyiotuser.service.IUserService;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 格子
 * @since 2020-03-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User getByUserName(String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername,username));
    }
}
