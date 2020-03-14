package com.tounans.easyiot.easyiotwss.wss;


import com.tounans.easyiot.common.client.UserClient;
import com.tounans.easyiot.common.entity.user.User;
import com.tounans.easyiot.easyiotwss.servier.IAuthService;
import com.tounans.easyiot.easyiotwss.servier.IUserService;
import com.tounans.easyiot.easyiotwss.utils.SpringUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    public static AttributeKey<User> key = AttributeKey.valueOf("userName");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        String url = request.uri();
        log.info("url");
        if(-1 != url.indexOf("/ws")) {


            String token = url.substring(url.indexOf("?token=")+7, url.length());
            IAuthService authService = SpringUtil.getBean(IAuthService.class);
            User user = authService.getUserByToken(token);

            UserChannlRel.put(user.getId()+"",ctx.channel());
            ctx.channel().attr(key).set(user);
            request.setUri("/ws");
            // 传递到下一个handler：升级握手
            ctx.fireChannelRead(request.retain());
        } else {
            log.error("not socket");
            ctx.close();
        }

//        if(-1 != url.indexOf("/ws")) {
//            String temp [] = url.split(";");
//            String name = URLDecoder.decode(temp[1], "UTF-8");
//            ctx.channel().attr(key).set(name);
//
//            // 传递到下一个handler：升级握手
//            ctx.fireChannelRead(request.retain());
//        } else {
//            System.out.println("not socket");
//            ctx.close();
//        }

    }

}
