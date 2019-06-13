package cn.liuht.im.common.protocol.request;

import cn.liuht.im.common.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import static cn.liuht.im.common.protocol.command.Command.CREATE_GROUP_REQUEST;

/**
 * 创建群聊数据包
 *
 * @author liuht
 * 2019/6/13 15:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
