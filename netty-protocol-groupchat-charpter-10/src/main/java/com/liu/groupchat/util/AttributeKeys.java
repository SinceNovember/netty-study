package com.liu.groupchat.util;

import io.netty.util.AttributeKey;

public class AttributeKeys {
    public static AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    public static AttributeKey<Long> USER_ID = AttributeKey.newInstance("userId");

}
