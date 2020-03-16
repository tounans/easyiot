package com.tounans.easyiot.easyiotwss.wss;

import com.tounans.easyiot.easyiotwss.config.WssConfig;
import com.tounans.easyiot.easyiotwss.utils.SpringUtil;
import com.tounans.easyiot.easyiotwss.utils.SslUtil;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/**
 * 初始化器
 */

public class WssServerInitialzer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        WssConfig wssConfig =(WssConfig) SpringUtil.getBean("wssConfig");

        ChannelPipeline pipeline = channel.pipeline();

        SSLContext sslContext = SslUtil.createSSLContext(wssConfig.getSslType(),wssConfig.getSslPath(),wssConfig.getSslPass());
        SSLEngine engine = sslContext.createSSLEngine();
        engine.setUseClientMode(false);

        pipeline.addLast(new SslHandler(engine));
//       ==========================SSL

//        websocket基于Http协议 所以要有http编码器
        pipeline.addLast(new HttpServerCodec());

//        对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());


//        对HttpMessage 进行聚合，聚合成 FullHttpRequest 或者 FullHttpResponse
//        几乎在Netty中的编程 都会用到此hanler

        pipeline.addLast(new HttpObjectAggregator(1024*64));

        pipeline.addLast(new HttpRequestHandler());
//       ============================以上支持Http协议===================

        // ====================== 增加心跳支持 start    ======================
        // 针对客户端，如果在1分钟时没有向服务端发送读写心跳(ALL)，则主动断开
        // 如果是读空闲或者写空闲，不处理
        pipeline.addLast(new IdleStateHandler(800, 1000, 1200));
        // 自定义的空闲状态检测
        pipeline.addLast(new HeartBeatHandler());
        // ====================== 增加心跳支持 end    ======================



// ====================== 以下是支持httpWebsocket ======================


        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        pipeline.addLast(new MsgHandler());


    }
}
