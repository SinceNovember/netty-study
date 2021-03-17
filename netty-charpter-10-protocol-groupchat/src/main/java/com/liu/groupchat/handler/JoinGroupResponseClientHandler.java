package com.liu.groupchat.handler;

import com.liu.groupchat.packet.JoinGroupResponsePacket;
import com.liu.groupchat.util.ChannelUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 客户端响应是否同意加入群聊
 */

@ChannelHandler.Sharable
public class JoinGroupResponseClientHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket joinGroupResponse) throws Exception {
        Long groupId = joinGroupResponse.getGroupId();
        System.out.println("您已经加入ID为" + groupId +"的群聊");
    }
}
