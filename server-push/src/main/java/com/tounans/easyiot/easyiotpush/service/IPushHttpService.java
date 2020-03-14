package com.tounans.easyiot.easyiotpush.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tounans.easyiot.common.entity.push.PushHttp;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 格子
 * @since 2020-01-04
 */
public interface IPushHttpService extends IService<PushHttp> {

    IPage<PushHttp> pageByUserParam(Integer page,Integer pageSize, Integer userId);

    PushHttp getByUserAndUserPushId(Integer userId, Integer userPushId);

    List<PushHttp> listByUserId(Integer userId);

    boolean saveOrUpdatePushHttp(Integer userId, PushHttp pushHttp);
}
