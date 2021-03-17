package com.liu.groupchat.handler;

import com.liu.groupchat.packet.MsgRequestPacket;
import com.liu.groupchat.packet.MsgResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MsgRequestHandler extends SimpleChannelInboundHandler<MsgRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgRequestPacket msg) throws Exception {
        String message = msg.getMessage();
        System.out.println("收到客户端消息：" + message);

        MsgResponsePacket msgResponsePacket = new MsgResponsePacket();
        msgResponsePacket.setMessage("你好，客户端");
        ctx.channel().writeAndFlush(msgResponsePacket);
    }
}
