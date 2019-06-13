package cn.liuht.im.common.protocol.response;

import cn.liuht.im.common.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static cn.liuht.im.common.protocol.command.Command.LOGOUT_RESPONSE;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 15:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGOUT_RESPONSE;
    }
}
