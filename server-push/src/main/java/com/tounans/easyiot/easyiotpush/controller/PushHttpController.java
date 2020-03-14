package com.tounans.easyiot.easyiotpush.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tounans.easyiot.common.entity.push.PushHttp;
import com.tounans.easyiot.easyiotpush.service.IPushHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pushHttp")
public class PushHttpController {

    @Autowired
    IPushHttpService pushHttpService;

    /**
     * 分页 Push http
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/pushHttpList")
    public IPage pushHttpList(Integer userId,@RequestParam(defaultValue = "1", required = false) Integer page,Integer pageSize){
        return pushHttpService.pageByUserParam(page,pageSize,userId);
    }

    /**
     * 通过 user push http id 获得
     * @param userId
     * @param userPushId
     * @return
     */
    @RequestMapping("/getPushHttpById")
    public PushHttp Edit(Integer userId, Integer userPushId){
        return pushHttpService.getByUserAndUserPushId(userId, userPushId);
    }

    /**
     * 保存或者更新
     * @param userId
     * @param pushHttp
     * @return
     */
    @RequestMapping( "/editAndSavePushHttp")
    public boolean edit(Integer userId,@RequestBody PushHttp pushHttp) {
        return pushHttpService.saveOrUpdatePushHttp(userId, pushHttp);

    }

    /**
     * 获得所有
     * @param userId
     * @return
     */
    @RequestMapping("/getAllPushHttp")
    public List<PushHttp> getAllPushHttp(Integer userId){
        return pushHttpService.listByUserId(userId);
    }

}
