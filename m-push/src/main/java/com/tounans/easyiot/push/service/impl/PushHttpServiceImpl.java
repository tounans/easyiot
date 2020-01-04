package com.tounans.easyiot.push.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tounans.easyiot.push.entity.PushHttp;
import com.tounans.easyiot.push.mapper.PushHttpMapper;
import com.tounans.easyiot.push.service.IPushHttpService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 格子
 * @since 2020-01-04
 */
@Service
public class PushHttpServiceImpl extends ServiceImpl<PushHttpMapper, PushHttp> implements IPushHttpService {


    private int pageSize = 20;

    private Integer getLastUserPushId(Integer userId){
        Integer result = baseMapper.getLastUserPushId(userId);
        return result==null?1:result;
    }

    @Override
    public IPage<PushHttp> pageByUserParam(Integer page, Integer userId) {
        Page<PushHttp> pushHttpPage = new Page<PushHttp>().setCurrent(page).setSize(pageSize).addOrder(OrderItem.asc("user_push_id"));
        QueryWrapper<PushHttp> pushHttpQueryWrapper = new QueryWrapper<>();
        if (userId!=null){
            pushHttpQueryWrapper.lambda().eq(PushHttp::getUserId,userId);
        }
        return baseMapper.selectPage(pushHttpPage,pushHttpQueryWrapper);
    }

    @Override
    public PushHttp getByUserAndUserPushId(Integer userId, Integer userPushId) {
        return this.getOne(new QueryWrapper<PushHttp>().lambda().eq(PushHttp::getUserId,userId).eq(PushHttp::getUserPushId,userPushId));
    }

    @Override
    public List<PushHttp> listByUserId(Integer userId) {
        return list(new QueryWrapper<PushHttp>().lambda().eq(PushHttp::getUserId,userId));
    }

    @Override
    public boolean saveOrUpdatePushHttp(Integer userId, PushHttp pushHttp) {

        pushHttp.setUserId(userId);

        if (pushHttp.getUserPushId() != null){
            PushHttp ByUserAndUserPushId = this.getByUserAndUserPushId(userId, pushHttp.getUserPushId());
            pushHttp.setId(ByUserAndUserPushId.getId());
        }

        if (pushHttp.getUserPushId() == null){
            Integer lastUserPushId = this.getLastUserPushId(userId);
            pushHttp.setUserPushId(lastUserPushId);
            pushHttp.setAddTime(LocalDateTime.now());
        }

        return saveOrUpdate(pushHttp);
    }
}
