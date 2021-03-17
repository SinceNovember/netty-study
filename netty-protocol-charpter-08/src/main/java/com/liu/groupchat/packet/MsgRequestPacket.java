package com.liu.groupchat.packet;

import lombok.Data;

/**
 * 消息请求包
 */
@Data
public class MsgRequestPacket extends Packet{
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MSG_REQUEST;
    }
}
