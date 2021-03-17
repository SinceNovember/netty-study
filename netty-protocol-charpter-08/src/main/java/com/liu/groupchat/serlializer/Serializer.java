package com.liu.groupchat.serlializer;

public interface Serializer {
    //获取序列化方式
    byte getSerializerAlgorithm();

    //序列化方法
    byte[] serialize(Object object);

    //反序列化
    <T> T deserialize(Class<T> clazz, byte[] bytes);

    Serializer DEFAULT = new JsonSerializer();
}
