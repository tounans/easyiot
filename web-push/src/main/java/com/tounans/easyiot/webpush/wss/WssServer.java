package com.tounans.easyiot.webpush.wss;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
@Component
@Slf4j
public class WssServer {

    @Autowired
    WssConfig wssConfig;

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    private static WssServer wssServer = null;

    private WssServer(){
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();

        server.group(mainGroup,subGroup)
                .channel(NioServerSocketChannel.class)
//                初始化拦截器
                .childHandler(new WssServerInitialzer());
    };


    public WssServer getWwserver(){
        if(wssServer == null){
            wssServer = new WssServer();
        }

        return wssServer;
    };

    public void start(){
        this.future =server.bind(8888);

//        this.future =server.bind(wssConfig.getPort());
        log.info("Netty Start port:"+wssConfig.getPort());
    }


    @PreDestroy
    public void destroy() {
        mainGroup.shutdownGracefully();
        subGroup.shutdownGracefully();
        log.warn("Netty Close");
    }
}
