package com.liu.groupchat.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

public class AuthHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //如果没有登录，则关闭连接
        Boolean login =(Boolean) ctx.channel().attr(AttributeKey.newInstance("login")).get();
        if (login == null || !login) {
            ctx.channel().close();
        }
        // 否则的话就直接传给下一个handler处理
        else {
            ctx.pipeline().remove(this);//移除该handler,下此传送信息就不用在验证了
            ctx.fireChannelRead(msg);
        }
    }
}
