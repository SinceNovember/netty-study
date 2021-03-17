package com.liu.groupchat.handler;

import com.liu.groupchat.packet.MsgResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MsgResponseHandler extends SimpleChannelInboundHandler<MsgResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgResponsePacket msg) throws Exception {
        String message = msg.getMessage();
        System.out.println("收到"+msg.getFromUserId()+"发来的消息：" + message);
    }
}
