package com.liu.groupchat.handler;

import com.liu.groupchat.packet.LoginRequestPacket;
import com.liu.groupchat.packet.LoginResponsePacket;
import com.liu.groupchat.util.AttributeKeys;
import com.liu.groupchat.util.ChannelUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 使用单例的登录请求，减小开销，须添加Sharable注解，表示此Handler可以在多线程下使用
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    private int i = 1;
    private LoginRequestHandler(){}
    //静态内部类
    private static class LoginRequestHandlerHandlerBuilder{
        private static LoginRequestHandler instance = new LoginRequestHandler();
    }
    //提供对外获取单例对象的方法
    public static LoginRequestHandler getInstance(){
        return LoginRequestHandlerHandlerBuilder.instance;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket packet) throws Exception {
        String username = packet.getUsername();
        System.out.println(username + "用户登录成功");
        //登录后返回客户端响应，告诉客户端是否登录成功
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(packet.getVersion());
        loginResponsePacket.setSuccess(true);

        ctx.channel().attr(AttributeKeys.LOGIN).set(true);

        // 为channel添加userId属性，方便在删除channel时能快速从Map中查找并删除
        ctx.channel().attr(AttributeKeys.USER_ID).set(packet.getUserId());
        // 保存channel
        ChannelUtil.saveChannel(packet.getUserId(), ctx.channel());
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    //断开连接时，移除对应Channel信息
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ChannelUtil.removeChannel(ctx.channel().attr(AttributeKeys.USER_ID).get());
    }

}
