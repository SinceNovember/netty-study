package com.liu.groupchat.handler;

import com.liu.groupchat.packet.CreateGroupRequestPacket;
import com.liu.groupchat.packet.CreateGroupResponsePacket;
import com.liu.groupchat.util.ChannelGroupUtil;
import com.liu.groupchat.util.ChannelUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

/**
 * 邀请加入群信息Handler
 */
@ChannelHandler.Sharable
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        System.out.println("收到加"+ createGroupResponsePacket.getGroupName() + "群的通知");

    }
}
