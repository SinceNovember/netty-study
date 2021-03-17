package com.liu.groupchat.handler;

import com.liu.groupchat.packet.JoinGroupRequestPacket;
import com.liu.groupchat.packet.JoinGroupResponsePacket;
import com.liu.groupchat.util.ChannelGroupUtil;
import com.liu.groupchat.util.ChannelUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * 客户端响应是否同意加入群聊
 */
@ChannelHandler.Sharable
public class JoinGroupResponseServerHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket joinGroupResponse) throws Exception {
        Long userId = joinGroupResponse.getUserId();
        Channel channel = ChannelUtil.getChannel(userId);
        if(channel != null){
            channel.writeAndFlush(joinGroupResponse);
        }else {
            System.out.println("用户"+userId+"不在线，持久化信息及通知");
        }
    }
}
