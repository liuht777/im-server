package cn.liuht.im.common.protocol.response;

import cn.liuht.im.common.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import static cn.liuht.im.common.protocol.command.Command.CREATE_GROUP_RESPONSE;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 15:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CreateGroupResponsePacket extends Packet {

    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}
