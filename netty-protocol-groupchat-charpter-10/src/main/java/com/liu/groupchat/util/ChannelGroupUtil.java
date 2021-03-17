package com.liu.groupchat.util;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 群组与通道工具
 */
public class ChannelGroupUtil {

    //使用ConcurrentHashMap保存映射关系
    //群ID与ChannelGroup的映射
    private static final Map<Long, ChannelGroup> groupMap = new ConcurrentHashMap<>();
    //群ID与群主id的映射
    private static final Map<Long, Long> groupLeaderMap = new ConcurrentHashMap<>();

    public static void addGroup(Long groupId, Long groupLeaderId, ChannelGroup channels){
        groupMap.put(groupId, channels);
        groupLeaderMap.put(groupId, groupLeaderId);
    }

    public static ChannelGroup getChannelGroup(Long groupId){
        return groupMap.get(groupId);
    }

    public static Long getGroupLeaderId(Long groupId){
        return groupLeaderMap.get(groupId);
    }
}
