package cn.liuht.im.common.protocol.request;

import cn.liuht.im.common.protocol.Packet;

import static cn.liuht.im.common.protocol.command.Command.HEARTBEAT_REQUEST;

/**
 * 心跳包
 *
 * @author liuht
 * 2019/6/13 17:13
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
