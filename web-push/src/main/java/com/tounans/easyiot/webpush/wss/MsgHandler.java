package com.tounans.easyiot.webpush.wss;

import com.alibaba.fastjson.JSONObject;
import com.fcibook.quick.http.QuickHttp;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MsgHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    public static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info(msg.text());

        String content = msg.text();
        Channel channel = ctx.channel();
        try {

            JSONObject jsonObject = JSONObject.parseObject(content);

            String token = jsonObject.getString("token").toUpperCase();
            String method = jsonObject.getString("method").toUpperCase();
            log.info(jsonObject.toJSONString());

            //TODO: 身份验证
            boolean checkUser = true;
            String userId = "1";
            if(checkUser){

                String imei = jsonObject.getString("imei");

               //TODO: WSS 对 MQTT通讯
                if (method.equals("KEEPALIVE")||method.equals("CONNECT")){
//                  如果是心跳。第一次连接就保存到UserChannlRel中
                    UserChannlRel.put(userId,channel);
                }else if (method.equals("TTS")){
//                    播放TTS
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            new QuickHttp().url("http://127.0.0.1:8010/send/tts").addParame("imei",imei).addParame("msg",jsonObject.getString("msg")).post().text();
                        }
                    }).start();
                }else if(method.equals("GPIOOUTPUT")){
//                    管脚输出
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            new QuickHttp().url("http://127.0.0.1:8010/send/gpioOutput")
                                    .addParame("imei",imei)
                                    .addParame("gpio",jsonObject.getString("gpio"))
                                    .addParame("state",jsonObject.getString("state"))
                                    .post().text();
                        }
                    }).start();
                }else if(method.equals("GETGPIOVAL")){
//                    获得管脚电平
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            new QuickHttp().url("http://127.0.0.1:8010/send/getGpioVal")
                                    .addParame("imei",imei)
                                    .addParame("gpio",jsonObject.getString("gpio"))
                                    .post().text();
                        }
                    }).start();
                }else if(method.equals("UARTOUTPUT")){
//                    uart输出
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            new QuickHttp().url("http://127.0.0.1:8010/send/uartOutput")
                                    .addParame("imei",imei)
                                    .addParame("uart",jsonObject.getString("uart"))
                                    .addParame("msg",jsonObject.getString("msg"))
                                    .post().text();
                        }
                    }).start();
                }

            }else{
                channel.close();
            }







        }catch (Exception ex){
//            如果传过来的不是 json 直接断开连接
            channel.close();
        }
    }


    /**
     * 当客户端连接服务端之后（打开连接）
     * 获取客户端的channle，并且放到ChannelGroup中去进行管理
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        users.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        UserChannlRel.remove(ctx.channel());
        // 当触发handlerRemoved，ChannelGroup会自动移除对应客户端的channel
        users.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 发生异常之后关闭连接（关闭channel），随后从ChannelGroup中移除
        UserChannlRel.remove(ctx.channel());
        ctx.channel().close();
        users.remove(ctx.channel());
    }
}
