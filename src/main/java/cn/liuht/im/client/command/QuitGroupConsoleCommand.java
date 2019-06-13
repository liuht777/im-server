package cn.liuht.im.client.command;

import cn.liuht.im.common.protocol.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 15:55
 */
@Slf4j
public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(final Scanner scanner, final Channel channel) {
        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();

        log.info("输入 groupId，退出群聊：");
        String groupId = scanner.next();

        quitGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
