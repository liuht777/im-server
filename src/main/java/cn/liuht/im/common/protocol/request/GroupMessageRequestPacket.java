package cn.liuht.im.common.protocol.request;

import cn.liuht.im.common.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static cn.liuht.im.common.protocol.command.Command.GROUP_MESSAGE_REQUEST;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 16:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMessageRequestPacket extends Packet {

    private String toGroupId;

    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }
}
