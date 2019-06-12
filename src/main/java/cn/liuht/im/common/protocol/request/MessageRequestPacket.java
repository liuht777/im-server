package cn.liuht.im.common.protocol.request;

import cn.liuht.im.common.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static cn.liuht.im.common.protocol.command.Command.MESSAGE_REQUEST;

/**
 * 发送消息数据包
 *
 * @author liuht
 * 2019/6/12 17:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
