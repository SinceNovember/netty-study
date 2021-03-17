package com.liu.groupchat.handler;

import com.liu.groupchat.packet.JoinGroupRequestPacket;
import com.liu.groupchat.util.ChannelGroupUtil;
import com.liu.groupchat.util.ChannelUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 服务器端处理加入群聊请求：找到群主ID，将请求转发给群主
 */
@ChannelHandler.Sharable
public class JoinGroupRequestServerHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequest) throws Exception {
        long groupId = joinGroupRequest.getGroupId();

        // 1）获取群主ID，将消息转发到群主
        Long groupLeaderId = ChannelGroupUtil.getGroupLeaderId(groupId);

        if(groupLeaderId != null){
            Channel channel = ChannelUtil.getChannel(groupLeaderId);
            channel.writeAndFlush(joinGroupRequest);
        }else {
            System.out.println("群主不在线，持久化请求信息......");
        }


    }
}
