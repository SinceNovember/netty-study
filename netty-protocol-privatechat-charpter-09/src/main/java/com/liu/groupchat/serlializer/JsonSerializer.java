package com.liu.groupchat.serlializer;

import com.alibaba.fastjson.JSON;

/**
 * fastJson序列化
 */
public class JsonSerializer implements Serializer{
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON_SERIALIZER;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
