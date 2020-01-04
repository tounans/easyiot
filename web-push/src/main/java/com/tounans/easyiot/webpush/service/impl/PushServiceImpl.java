package com.tounans.easyiot.webpush.service.impl;

import com.fcibook.quick.http.QuickHttp;
import com.tounans.easyiot.push.entity.PushHttp;
import com.tounans.easyiot.webpush.service.IPushService;
import org.springframework.stereotype.Service;

@Service
public class PushServiceImpl implements IPushService {
    @Override
    public boolean pushHttp(String imei, String data, PushHttp pushHttp) {
        int stateCode = new QuickHttp().url(pushHttp.getUrl())
                .addParame("imei", imei)
                .addParame("data", data)
                .addParame("token", pushHttp.getToken())
                .setConnectionTimeout(pushHttp.getTimeOut()).post().body().getStateCode();
        if ( stateCode == 200){
            return  true;
        }
        return false;
    }
}
