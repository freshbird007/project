package com.leaves.smalltiger.common.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

@Component
public class RedisUtilsSerializable implements RedisSerializer<Object> {
    Converter<Object, byte[]> serializingConverter = new SerializingConverter();
    Converter<byte[], Object> deserializingConverter = new DeserializingConverter();
    byte[]  EMPTY = new byte[0];
    //    序列化
    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if(o==null){
            return EMPTY;
        }
        return serializingConverter.convert(o);

    }

    //    反序列化
    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        Object result = null;
        if (bytes != null) {
            result = deserializingConverter.convert(bytes);
        }
        return result;
    }
}
