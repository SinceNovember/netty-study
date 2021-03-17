package com.liu.groupchat.packet;

import lombok.Data;

import java.util.List;

/**
 * 创建群聊包
 */
@Data
public class JoinGroupRequestPacket extends Packet{
    private long groupId;  //群ID
    private long userId;  //用户Id
    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }
}
