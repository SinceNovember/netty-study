package com.liu.groupchat.packet;

import lombok.Data;

import java.util.List;

/**
 * 创建群聊包
 */
@Data
public class CreateGroupRequestPacket extends Packet{
    private String groupName;  //群名
    private Long groupLeader;  //群主ID
    private List<Long> userIdList;  //群成员ID
    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
