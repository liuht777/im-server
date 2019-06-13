package cn.liuht.im.client.console;

import cn.liuht.im.common.protocol.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 15:07
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(final Scanner scanner, final Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
