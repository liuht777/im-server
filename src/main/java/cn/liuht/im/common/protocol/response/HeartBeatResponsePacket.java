package cn.liuht.im.common.protocol.response;

import cn.liuht.im.common.protocol.Packet;

import static cn.liuht.im.common.protocol.command.Command.HEARTBEAT_RESPONSE;

/**
 * 心跳响应包
 *
 * @author liuht
 * 2019/6/13 17:18
 */
public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
