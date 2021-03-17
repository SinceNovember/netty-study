package com.liu.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 聊天服务器
 */
public class SimpleChatServerHandler extends SimpleChannelInboundHandler<String> {
    //存放已连接的Channel
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        Channel incoming = channelHandlerContext.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER]-" + incoming.remoteAddress() + "加入\n");
        }
        channels.add(incoming);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        Channel incoming = channelHandlerContext.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER]-" + incoming.remoteAddress() + "离开\n");
        }
        channels.remove(incoming);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel incoming = channelHandlerContext.channel();
        for (Channel channel : channels) {
            if (channel != incoming) {
                channel.writeAndFlush("[" + incoming.remoteAddress() + "]-" + s + "\n");
            } else {
                channel.writeAndFlush("[you]-" + s + "\n");
            }
        }
        channels.add(incoming);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception{
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "掉线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "异常");
        cause.printStackTrace();
        ctx.close();
    }
}
