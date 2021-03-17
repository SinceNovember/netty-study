package com.liu.production;

import io.netty.channel.ChannelHandler;

public interface ChannelHandlerHolder {
    ChannelHandler[] handlers();
}
