package cn.liuht.im.client.command;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 控制台命令
 *
 * @author liuht
 * 2019/6/13 14:58
 */
public interface ConsoleCommand {

    /**
     * 执行
     *
     * @param scanner
     * @param channel
     */
    void exec(Scanner scanner, Channel channel);
}
