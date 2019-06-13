package cn.liuht.im.client.command;

import cn.liuht.im.common.protocol.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * 加入群聊
 *
 * @author liuht
 * 2019/6/13 15:43
 */
@Slf4j
public class JoinGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(final Scanner scanner, final Channel channel) {
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();

        log.info("输入 groupId，加入群聊：");
        String groupId = scanner.next();

        joinGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
