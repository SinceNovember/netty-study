package com.liu.groupchat.packet;

import lombok.Data;

/**
 * 创建群聊包
 */
@Data
public class JoinGroupResponsePacket extends Packet{
    private boolean agree;
    private long groupId;  //群ID
    private long userId;  //用户Id
    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }
}
