package com.liu.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 将接受到的数据丢弃的handler
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //丢弃掉数据
//        ((ByteBuf) msg).release();
        /* 或者
        try {
	        // Do something with msg
	    } finally {
	        ReferenceCountUtil.release(msg);
	    }
        */

        ByteBuf in = (ByteBuf) msg;
        try{
            while (in.isReadable()) {
                System.out.println((char)in.readByte());
                System.out.println(in.readByte());
                System.out.flush();
            }
        } finally {
            // 释放消息
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
