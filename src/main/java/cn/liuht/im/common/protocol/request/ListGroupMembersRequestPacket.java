package cn.liuht.im.common.protocol.request;

import cn.liuht.im.common.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static cn.liuht.im.common.protocol.command.Command.LIST_GROUP_MEMBERS_REQUEST;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 15:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_REQUEST;
    }
}
