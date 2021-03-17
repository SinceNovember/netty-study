package com.liu.production.server.acceptor;

import java.net.SocketAddress;

/**
 *
 *  netty server端的标准接口定义
 */
public interface SrvAcceptor {

    SocketAddress localAddress();

    void start() throws InterruptedException;

    void shutdownGracefully();

    void start(boolean sync) throws InterruptedException;

}
