package com.liu.groupchat.util;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelUtil {

    //定义一个Map结构存储userID映射到channel
    private static final Map<Long, Channel> userIdChannel = new ConcurrentHashMap<>();

    public static void saveChannel(Long userId, Channel channel) {
        userIdChannel.put(userId, channel);
    }

    //当用户退出后删除用户channel
    public static void removeChannel(Long userId){
        if(!userIdChannel.containsKey(userId)){
            throw new RuntimeException("未找到当前用户的channel");
        }
        userIdChannel.remove(userId);
    }

    //通过userId查找channel
    public static Channel getChannel(Long userId){
        return userIdChannel.get(userId);
    }

}
