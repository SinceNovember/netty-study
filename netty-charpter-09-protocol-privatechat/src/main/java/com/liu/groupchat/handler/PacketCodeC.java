package com.liu.groupchat.handler;

import com.liu.groupchat.packet.*;
import com.liu.groupchat.serlializer.JsonSerializer;
import com.liu.groupchat.serlializer.Serializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义的加解密
 */
public class PacketCodeC {
    //魔数，由于int正好是4字节，所以用int表示即可
    private static final int MAGIC_NUMBER = 0x15794516;
    //用于通过command指令找到具体的Java类
    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;

    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(Command.MSG_REQUEST, MsgRequestPacket.class);
        packetTypeMap.put(Command.MSG_RESPONSE, MsgResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JsonSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public static class Holder{
        private static PacketCodeC INSTANCE = new PacketCodeC();

        public static PacketCodeC getInstance(){
            return INSTANCE;
        }
    }

    /**
     * 编码
     */
    public void encode(Packet packet, ByteBuf byteBuf) {
        //序列化Java对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        //编码成标准通信协议数据包
        byteBuf.writeInt(MAGIC_NUMBER);  //魔数
        byteBuf.writeByte(packet.getVersion());  //版本号
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());  //序列化算法
        byteBuf.writeByte(packet.getCommand());  //指令
        byteBuf.writeInt(bytes.length);  //数据段长度
        byteBuf.writeBytes(bytes);  //数据段

    }

    public Packet decode(ByteBuf byteBuf) {
        int magic_number = byteBuf.readInt();
        if(magic_number != MAGIC_NUMBER)
            throw new RuntimeException("解析错误");

        byteBuf.skipBytes(1);  //先不处理版本号
        //序列化标识
        byte serializeAlgorithm = byteBuf.readByte();
        //指令
        byte command = byteBuf.readByte();
        //数据包长度
        int length = byteBuf.readInt();
        //读取数据
        byte[] data = new byte[length];
        byteBuf.readBytes(data);

        Class<? extends Packet> requestType = packetTypeMap.get(command);
        Serializer serializer = serializerMap.get(serializeAlgorithm);

        if(requestType != null && serializer != null){
            return serializer.deserialize(requestType, data);
        }
        return null;
    }
}
