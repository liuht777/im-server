package cn.liuht.im.common.attribute;

import io.netty.util.AttributeKey;

/**
 * Attributes
 *
 * @author liuht
 * 2019/6/12 17:16
 */
public interface Attributes {

    /**
     * 登录标识
     */
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
