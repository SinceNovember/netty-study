package com.liu.groupchat.server;

import com.liu.groupchat.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class Server {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(12);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //心跳判断包须放在第一个
                            pipeline.addLast(new PacketDecoder());
                            pipeline.addLast(LoginRequestHandler.getInstance());
                            pipeline.addLast(new PacketEncoder());
                            pipeline.addLast(new MsgRequestHandler());
                            pipeline.addLast(new MsgResponseHandler());
                            pipeline.addLast(new CreateGroupRequestHandler());
                            pipeline.addLast(new JoinGroupRequestServerHandler());
                            pipeline.addLast(new JoinGroupResponseServerHandler());

                        }
                    });

            //启动服务器
            ChannelFuture bind = serverBootstrap.bind(6666).sync();
            System.out.println("服务器启动");
            //监听关闭
            bind.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
