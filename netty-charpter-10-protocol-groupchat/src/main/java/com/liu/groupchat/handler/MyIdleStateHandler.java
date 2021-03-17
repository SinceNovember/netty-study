package com.liu.groupchat.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 心跳状态Handler 用来定义断开连接的时常以及处理方式,须放在第一个
 */
public class MyIdleStateHandler extends IdleStateHandler {
    public MyIdleStateHandler() {
        //读空闲、写空闲、读写空闲
        super(0, 0, 10);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println("10秒未读取到客户端的数据，关闭该客户端连接");
        ctx.channel().close();
    }

}
