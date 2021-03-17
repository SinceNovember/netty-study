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
public class JoinGroupRequestClientHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequest) throws Exception {
        Long groupId = joinGroupRequest.getGroupId();
        Long userId = joinGroupRequest.getUserId();
        System.out.println(userId + "申请加入ID为"+groupId+"的群聊");

        //这里先默认同意加群
        ChannelGroup channelGroup = ChannelGroupUtil.getChannelGroup(groupId);

        // 1）获取群主ID，将消息转发到群主
        Long groupLeaderId = ChannelGroupUtil.getGroupLeaderId(groupId);

        if(groupLeaderId != null){
            Channel channel = ChannelUtil.getChannel(groupLeaderId);
            channel.writeAndFlush(joinGroupRequest);
        }else {
            System.out.println("群主不在线，持久化请求信息......");
        }

        //构造response
        JoinGroupResponsePacket joinGroupResponse = new JoinGroupResponsePacket();
        joinGroupResponse.setAgree(true);
        joinGroupResponse.setGroupId(groupId);
        joinGroupResponse.setUserId(userId);
        ctx.channel().writeAndFlush(joinGroupResponse);
    }
}
