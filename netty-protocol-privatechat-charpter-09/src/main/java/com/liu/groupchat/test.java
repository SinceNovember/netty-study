package com.liu.groupchat;

import com.liu.groupchat.handler.PacketCodeC;
import com.liu.groupchat.packet.LoginRequestPacket;
import com.liu.groupchat.serlializer.JsonSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class test {
    public static void main(String[] args) {
        JsonSerializer jsonSerializer = new JsonSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion((byte) 1);
        loginRequestPacket.setUserId(1);
        loginRequestPacket.setUsername("zhangsan");
        loginRequestPacket.setPassword("123456");

        PacketCodeC packetCodeC = new PacketCodeC();
        //编码
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        packetCodeC.encode(loginRequestPacket,byteBuf);
        //解码
        LoginRequestPacket decode =(LoginRequestPacket) packetCodeC.decode(byteBuf);
        System.out.println(decode.getUsername());

    }
}
