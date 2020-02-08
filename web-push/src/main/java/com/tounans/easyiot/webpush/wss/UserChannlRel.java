package com.tounans.easyiot.webpush.wss;

import io.netty.channel.Channel;
import java.util.HashMap;

public class UserChannlRel {

    private static HashMap<String, Channel> manager = new HashMap<>();

    public static void put(String userId,Channel channel){
        manager.put(userId,channel);
    }

    public static void remove(Channel channel){
        for (String key: manager.keySet()
             ) {
            Channel value = manager.get(key);
            if(value.id().asLongText().equals(channel.id().asLongText())){
                manager.remove(key);
            }
        }
    }
    public static Channel get(String userId){
        return  manager.get(userId);
    }
}
