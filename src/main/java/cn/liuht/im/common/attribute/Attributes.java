package cn.liuht.im.common.attribute;

import cn.liuht.im.common.model.Session;
import io.netty.util.AttributeKey;

/**
 * Attributes
 *
 * @author liuht
 * 2019/6/12 17:16
 */
public interface Attributes {

    /**
     * 回话标识
     */
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
