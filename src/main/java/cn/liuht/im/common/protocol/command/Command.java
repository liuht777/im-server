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

    /**
     * 发送请求消息
     */
    Byte MESSAGE_REQUEST = 3;

    /**
     * 发送响应消息
     */
    Byte MESSAGE_RESPONSE = 4;

    /**
     * 退出登录
     */
    Byte LOGOUT_REQUEST = 5;

    /**
     * 退出登录响应
     */
    Byte LOGOUT_RESPONSE = 6;

    /**
     * 创建群聊
     */
    Byte CREATE_GROUP_REQUEST = 7;

    /**
     * 创建群聊响应
     */
    Byte CREATE_GROUP_RESPONSE = 8;

}
