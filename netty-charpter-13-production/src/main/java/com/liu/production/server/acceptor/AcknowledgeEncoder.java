package com.liu.production.server.acceptor;

import com.liu.production.common.Acknowledge;
import static com.liu.production.common.NettyCommonProtocol.ACK;
import static com.liu.production.common.NettyCommonProtocol.MAGIC;
import static com.liu.production.serializer.SerializerHolder.serializerImpl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * ack的编码器
 */
@ChannelHandler.Sharable
public class AcknowledgeEncoder  extends MessageToByteEncoder<Acknowledge> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Acknowledge ack, ByteBuf out) throws Exception {
        byte[] bytes = serializerImpl().writeObject(ack);
        out.writeShort(MAGIC)
                .writeByte(ACK)
                .writeByte(0)
                .writeLong(ack.sequence())
                .writeInt(bytes.length)
                .writeBytes(bytes);

    }
}
