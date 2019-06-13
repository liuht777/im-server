package cn.liuht.im.client.console;

import cn.liuht.im.common.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 15:06
 */
@Slf4j
public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(final Scanner scanner, final Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        log.info("输入用户名登录: ");
        loginRequestPacket.setUserName(scanner.nextLine());
        // 使用默认密码
        loginRequestPacket.setPassword("pwd");

        // 发送登录数据包
        channel.writeAndFlush(loginRequestPacket);
        waitForLoginResponse();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {

        }
    }
}
