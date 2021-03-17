package com.liu.groupchat.handler;

import com.liu.groupchat.packet.MsgRequestPacket;
import com.liu.groupchat.packet.MsgResponsePacket;
import com.liu.groupchat.util.AttributeKeys;
import com.liu.groupchat.util.ChannelUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class MsgRequestHandler extends SimpleChannelInboundHandler<MsgRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgRequestPacket msg) throws Exception {
        String message = msg.getMessage();
        long fromUserId = ctx.channel().attr(AttributeKeys.USER_ID).get();
        System.out.println("收到userId："+fromUserId +"发送给"+msg.getToUserId()+"的消息："+ message);

        //构造发送给接收方的消息
        MsgResponsePacket msgResponsePacket = new MsgResponsePacket();
        msgResponsePacket.setMessage(message);
        msgResponsePacket.setFromUserId(fromUserId);
        //获取接收方与服务器端连接的channel，并向接收方发送数据
        Channel channel = ChannelUtil.getChannel(msg.getToUserId());
        if(channel == null){
            System.out.println(msg.getToUserId()+"不在线");
        }else {
            channel.writeAndFlush(msgResponsePacket);
        }
    }
}
