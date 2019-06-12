package cn.liuht.im.common.util;

import cn.liuht.im.common.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * 登录工具类
 *
 * @author liuht
 * 2019/6/12 17:17
 */
public class LoginUtil {

    /**
     * 标记已登录
     *
     * @param channel
     */
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    /**
     * 是否已登录
     *
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null && loginAttr.get();
    }

}
