package com.tounans.easyiot.easyiotwss.wss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class WssBooter implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private WssServer wssServer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("11111111111");
//        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            wssServer.start();
//        }
    }
}
