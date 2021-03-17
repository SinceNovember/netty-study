package com.liu.production.example;

import com.liu.production.server.acceptor.DefaultCommonSrvAcceptor;

/**
 * 服务端启动类
 */
public class SrvAcceptorStartup  {
    public static void main(String[] args) throws InterruptedException {

        DefaultCommonSrvAcceptor defaultCommonSrvAcceptor = new DefaultCommonSrvAcceptor(20011,null);
        defaultCommonSrvAcceptor.start();

    }
}
