package com.tounans.easyiot.push.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tounans.easyiot.push.entity.PushHttp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 格子
 * @since 2020-01-04
 */
public interface IPushHttpService extends IService<PushHttp> {

    IPage<PushHttp> pageByUserParam(Integer page, Integer userId);

    PushHttp getByUserAndUserPushId(Integer userId, Integer userPushId);

    boolean saveOrUpdatePushHttp(Integer userId, PushHttp pushHttp);
}
