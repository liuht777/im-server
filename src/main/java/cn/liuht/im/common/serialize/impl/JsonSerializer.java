package cn.liuht.im.common.serialize.impl;

import cn.liuht.im.common.serialize.Serializer;
import cn.liuht.im.common.serialize.SerializerAlgorithm;
import com.alibaba.fastjson.JSON;

/**
 * JSON 序列化实现
 *
 * @author liuht
 * 2019/6/12 15:45
 */
public class JsonSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(final Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(final Class<T> clazz, final byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
