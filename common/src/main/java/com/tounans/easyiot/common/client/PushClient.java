package com.tounans.easyiot.common.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tounans.easyiot.common.entity.push.PushHttp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "server-push")
public interface PushClient {


    /**
     * 分页 Push http
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/pushHttp/pushHttpList")
    IPage pushHttpList(@RequestParam("userId") Integer userId,
                       @RequestParam(defaultValue = "1", required = false) Integer page,
                       @RequestParam("pageSize") Integer pageSize);

    /**
     * 通过 user push http id 获得
     * @param userId
     * @param userPushId
     * @return
     */
    @RequestMapping("/pushHttp/getPushHttpById")
    PushHttp Edit(@RequestParam("userId") Integer userId,
                  @RequestParam("userPushId") Integer userPushId);

    /**
     * 保存或者更新
     * @param userId
     * @param pushHttp
     * @return
     */
    @RequestMapping( "/pushHttp/editAndSavePushHttp")
    boolean edit(@RequestParam("userId") Integer userId,
                 @RequestBody PushHttp pushHttp);

    /**
     * 获得所有
     * @param userId
     * @return
     */
    @RequestMapping("/pushHttp/getAllPushHttp")
    List<PushHttp> getAllPushHttp(@RequestParam("userId") Integer userId);
}
