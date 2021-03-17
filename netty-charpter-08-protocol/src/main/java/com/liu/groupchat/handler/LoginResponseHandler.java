package com.liu.groupchat.handler;

import com.liu.groupchat.packet.LoginRequestPacket;
import com.liu.groupchat.packet.LoginResponsePacket;
import com.liu.groupchat.packet.MsgRequestPacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Scanner;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("开始登陆");
        System.out.println("请输入用户名");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("请输入密码");
        String password = scanner.nextLine();

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(100);
        loginRequestPacket.setUsername(username);
        loginRequestPacket.setPassword(password);

        ctx.channel().writeAndFlush(loginRequestPacket);
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        if(loginResponsePacket.isSuccess()){
            System.out.println("登录成功");
            //登录成功后，启动一个线程接受控制台输入，并发送数据
            startSendMsgThread(ctx.channel());
        }else {
            System.out.println(loginResponsePacket.getReason());
        }
    }

    private static void startSendMsgThread(Channel channel) {
        new Thread(() -> {
            System.out.println("输入消息后回车发送到服务器端");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                String msg = scanner.nextLine();
                MsgRequestPacket msgRequestPacket = new MsgRequestPacket();
                msgRequestPacket.setMessage(msg);
                //像服务端发送消息
                channel.writeAndFlush(msgRequestPacket);
            }

        }).start();
    }


}
