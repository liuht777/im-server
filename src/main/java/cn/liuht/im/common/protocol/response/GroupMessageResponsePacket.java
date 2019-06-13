package cn.liuht.im.common.protocol.response;

import cn.liuht.im.common.model.Session;
import cn.liuht.im.common.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static cn.liuht.im.common.protocol.command.Command.GROUP_MESSAGE_RESPONSE;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 16:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }
}
