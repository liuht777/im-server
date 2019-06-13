package cn.liuht.im.client.command;

import cn.liuht.im.common.protocol.request.MessageRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 15:10
 */
@Slf4j
public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(final Scanner scanner, final Channel channel) {
        log.info("发送消息给某个用户：");
        String toUserId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
    }
}
