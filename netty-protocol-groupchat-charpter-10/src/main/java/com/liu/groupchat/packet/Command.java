package com.liu.groupchat.packet;

/**
 * 因为指令为一个字节 ，枚举默认是int，用接口好点
 * 注意：添加一个指令后，需在packetTypeMap添加对应指令->Package
 */
public interface Command {
    Byte LOGIN_REQUEST = 1;//表示这是一个登录请求

    Byte LOGIN_RESPONSE = 2;//表示这个一个登录响应

    Byte MSG_REQUEST = 0; //这是一个消息

    Byte MSG_RESPONSE = 9; //这是一个消息响应

    Byte CREATE_GROUP_REQUEST = 7; //创建群请求

    Byte CREATE_GROUP_RESPONSE = 8; //创建群请求

    Byte JOIN_GROUP_REQUEST = 5; //加入群请求

    Byte JOIN_GROUP_RESPONSE = 6; //加入群响应

    Byte HEART_BEAT_REQUEST = 3; //加入群请求

    Byte HEART_BEAT_RESPONSE = 4; //加入群响应


}
