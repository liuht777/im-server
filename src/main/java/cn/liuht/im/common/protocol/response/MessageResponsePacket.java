package cn.liuht.im.common.protocol.response;

import cn.liuht.im.common.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static cn.liuht.im.common.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * 响应消息数据包
 *
 * @author liuht
 * 2019/6/12 17:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageResponsePacket extends Packet {

    /**
     * 消息是谁发来的
     */
    private String fromUserId;

    /**
     * 消息是谁发来的
     */
    private String fromUserName;

    /**
     * 内容
     */
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
