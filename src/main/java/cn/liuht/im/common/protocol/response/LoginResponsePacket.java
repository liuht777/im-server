package cn.liuht.im.common.protocol.response;

import cn.liuht.im.common.protocol.Packet;
import cn.liuht.im.common.protocol.command.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登录响应数据包
 *
 * @author liuht
 * 2019/6/12 16:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
