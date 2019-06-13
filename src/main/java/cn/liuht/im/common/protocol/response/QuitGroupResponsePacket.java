package cn.liuht.im.common.protocol.response;

import cn.liuht.im.common.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static cn.liuht.im.common.protocol.command.Command.QUIT_GROUP_RESPONSE;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 15:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_RESPONSE;
    }
}
