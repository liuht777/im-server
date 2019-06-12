package cn.liuht.im.common.serialize;

import cn.liuht.im.common.serialize.impl.JsonSerializer;

/**
 * 序列化接口
 *
 * @author liuht
 * 2019/6/12 15:43
 */
public interface Serializer {

    Serializer DEFAULT = new JsonSerializer();

    /**
     * 序列化算法
     *
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     *
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     *
     * @param clazz
     * @param bytes
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
