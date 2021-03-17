package com.liu.groupchat.handler;

import com.liu.groupchat.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet msg, ByteBuf out) throws Exception {
        PacketCodeC.Holder.getInstance().encode(msg, out);
    }
}
