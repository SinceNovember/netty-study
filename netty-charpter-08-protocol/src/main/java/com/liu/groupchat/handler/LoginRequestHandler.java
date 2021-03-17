package com.liu.groupchat.handler;

import com.liu.groupchat.packet.LoginRequestPacket;
import com.liu.groupchat.packet.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket packet) throws Exception {
        String username = packet.getUsername();
        System.out.println(username + "用户登录成功");
        //登录后返回客户端响应，告诉客户端是否登录成功
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(packet.getVersion());
        loginResponsePacket.setSuccess(true);

        ctx.channel().writeAndFlush(loginResponsePacket);

    }
}
