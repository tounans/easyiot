package com.tounans.easyiot.easyiotwss.wss;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class UserChannlRel {

    private static ConcurrentHashMap<String, Channel> manager = new ConcurrentHashMap<>();

    public static synchronized void put(String userId,Channel channel){
        manager.put(userId,channel);
    }
    public static synchronized void remove(Channel channel){
        for (String key: manager.keySet()
             ) {
            Channel value = manager.get(key);
            if(value.id().asLongText().equals(channel.id().asLongText())){
                manager.remove(key);
            }
        }
    }
    public static synchronized Channel get(String userId){
        return  manager.get(userId);
    }
}
