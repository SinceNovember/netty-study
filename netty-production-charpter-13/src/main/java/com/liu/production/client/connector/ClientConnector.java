package com.liu.production.client.connector;

import io.netty.channel.Channel;

/**
 * 客户端连接接口
 */
public interface ClientConnector {
    Channel connect(int port, String host);

    void shutdownGracefully();
}
