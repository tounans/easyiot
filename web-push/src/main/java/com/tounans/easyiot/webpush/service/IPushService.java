package com.tounans.easyiot.webpush.service;

import com.tounans.easyiot.push.entity.PushHttp;

public interface IPushService {
    boolean pushHttp(String imei, String data, PushHttp pushHttp);
}
