package com.liu.groupchat.packet;

import lombok.Data;

import java.util.List;

/**
 * 创建群聊响应包
 */
@Data
public class CreateGroupResponsePacket extends Packet{
    private long groupId;  //群名
    private String groupName;
    private boolean success;  //群主ID
    private List<Long> userIdList;  //群成员ID

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
