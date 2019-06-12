package cn.liuht.im.common.protocol.command;

/**
 * 指令集合
 *
 * @author liuht
 * 2019/6/12 15:40
 */
public interface Command {

    /**
     * 登录指令
     */
    Byte LOGIN_REQUEST = 1;

    /**
     * 登录响应
     */
    Byte LOGIN_RESPONSE = 2;

}
