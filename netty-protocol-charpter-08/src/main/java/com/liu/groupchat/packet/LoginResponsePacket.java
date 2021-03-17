package com.liu.groupchat.packet;

import lombok.Data;

/**
 * 登录响应包
 */
@Data
public class LoginResponsePacket extends Packet{
    private boolean success;  //是否成功

    private String reason;   //失败的原因

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }

}
