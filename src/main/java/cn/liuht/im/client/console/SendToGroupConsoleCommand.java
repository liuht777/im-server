package cn.liuht.im.client.console;

import cn.liuht.im.common.protocol.request.GroupMessageRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 16:51
 */
@Slf4j
public class SendToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(final Scanner scanner, final Channel channel) {
        log.info("发送消息给某个某个群组：");

        String toGroupId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new GroupMessageRequestPacket(toGroupId, message));
    }
}
