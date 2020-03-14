package com.tounans.easyiot.easyiotuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tounans.easyiot.common.entity.user.User;


/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 格子
 * @since 2020-03-05
 */
public interface IUserService extends IService<User> {

    User getByUserName(String username);

}
