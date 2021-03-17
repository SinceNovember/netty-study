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
import java.util.UUID;

@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket requestPacket) throws Exception {
        List<Long> userIdList = requestPacket.getUserIdList();
        String groupName = requestPacket.getGroupName();
        Long groupLeaderId = requestPacket.getGroupLeader();

        //创建一个Channel分组
        DefaultChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        //遍历userIdList，将用户对应的channel添加到channel分组中
        for (Long userId: userIdList){
            Channel channel = ChannelUtil.getChannel(userId);
            if(channel != null){
                channelGroup.add(channel);
            }
            else {
                //TODO:如果不在线，将加群的请求存入数据库，当该用户登录时再去查询数据库是否有邀请群的通知
                System.out.println(userId + "不在线");
            }
        }

        long groupID = RandomUtils.nextLong(1, 1000);
        System.out.println(groupID);

        ChannelGroupUtil.addGroup(groupID, groupLeaderId, channelGroup);
        //创建response返回给客户端
        CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();
        responsePacket.setGroupId(groupID);  //这里暂时返回一个随机的long数字作为群聊ID
        responsePacket.setSuccess(true);
        responsePacket.setGroupName(groupName);
        responsePacket.setUserIdList(userIdList);

        //给所有客户端发送拉群通知
        channelGroup.writeAndFlush(responsePacket);

    }
}
