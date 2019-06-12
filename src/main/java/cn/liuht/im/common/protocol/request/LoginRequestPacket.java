package cn.liuht.im.common.protocol.request;

import cn.liuht.im.common.protocol.Packet;
import cn.liuht.im.common.protocol.command.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 登录数据包
 *
 * @author liuht
 * 2019/6/12 15:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRequestPacket extends Packet {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
