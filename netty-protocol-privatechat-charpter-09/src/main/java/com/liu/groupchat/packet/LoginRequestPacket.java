package com.liu.groupchat.packet;

import lombok.Data;

/**
 * 登录请求协议
 */
@Data
public class LoginRequestPacket extends Packet {
    private long userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
