package com.liu.groupchat.packet;

import lombok.Data;

/**
 * 消息响应包
 */
@Data
public class MsgResponsePacket extends Packet{
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MSG_RESPONSE;
    }
}
