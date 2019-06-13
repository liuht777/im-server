package cn.liuht.im.common.util;

import java.util.UUID;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 15:23
 */
public class IDUtil {

    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

}
