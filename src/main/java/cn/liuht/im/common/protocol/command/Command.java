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

    /**
     * 查询群聊请求
     */
    Byte LIST_GROUP_MEMBERS_REQUEST = 9;

    /**
     * 查询群聊成员响应
     */
    Byte LIST_GROUP_MEMBERS_RESPONSE = 10;

    /**
     * 加入群聊请求
     */
    Byte JOIN_GROUP_REQUEST = 11;

    /**
     * 加入群聊响应
     */
    Byte JOIN_GROUP_RESPONSE = 12;

    /**
     * 退出群聊请求
     */
    Byte QUIT_GROUP_REQUEST = 13;

    /**
     * 退出群聊响应
     */
    Byte QUIT_GROUP_RESPONSE = 14;

    /**
     * 发送群消息请求
     */
    Byte GROUP_MESSAGE_REQUEST = 15;

    /**
     * 发送群消息响应
     */
    Byte GROUP_MESSAGE_RESPONSE = 16;

    /**
     * 心跳请求
     */
    Byte HEARTBEAT_REQUEST = 17;

    /**
     * 心跳请求响应
     */
    Byte HEARTBEAT_RESPONSE = 18;

}
