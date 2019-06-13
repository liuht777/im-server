package cn.liuht.im.common.protocol.request;

import cn.liuht.im.common.protocol.Packet;

import static cn.liuht.im.common.protocol.command.Command.LOGOUT_REQUEST;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 15:08
 */
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}
