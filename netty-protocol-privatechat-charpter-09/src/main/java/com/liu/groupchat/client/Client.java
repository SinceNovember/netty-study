package com.liu.groupchat.client;

import com.liu.groupchat.handler.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new PacketDecoder()); //解码器
                            ch.pipeline().addLast(new LoginResponseHandler());  //登录返回时的处理器
                            ch.pipeline().addLast(new PacketEncoder());  //编码器
                            ch.pipeline().addLast(new MsgRequestHandler());
                            ch.pipeline().addLast(new MsgResponseHandler());

                        }
                    });

            ChannelFuture future = bootstrap.connect("localhost", 6666).sync();
            future.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }

}
