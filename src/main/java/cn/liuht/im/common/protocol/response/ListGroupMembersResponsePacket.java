package cn.liuht.im.common.protocol.response;

import cn.liuht.im.common.model.Session;
import cn.liuht.im.common.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import static cn.liuht.im.common.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 15:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
