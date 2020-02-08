package com.tounans.easyiot.webpush.wss;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component()
@Data
public class WssConfig {

    @Value("${netty.wss.sslType}")
    private String sslType;
    @Value("${netty.wss.sslPath}")
    private String sslPath;
    @Value("${netty.wss.sslPass}")
    private String sslPass;
    @Value("${netty.wss.port}")
    private Integer port;

}
