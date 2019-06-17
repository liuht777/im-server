package cn.liuht.im.client;

import cn.liuht.im.client.console.ConsoleCommandManager;
import cn.liuht.im.client.console.LoginConsoleCommand;
import cn.liuht.im.client.handler.CreateGroupResponseHandler;
import cn.liuht.im.client.handler.GroupMessageResponseHandler;
import cn.liuht.im.client.handler.HeartBeatResponseHandler;
import cn.liuht.im.client.handler.HeartBeatTimerHandler;
import cn.liuht.im.client.handler.JoinGroupResponseHandler;
import cn.liuht.im.client.handler.ListGroupMembersResponseHandler;
import cn.liuht.im.client.handler.LoginResponseHandler;
import cn.liuht.im.client.handler.LogoutResponseHandler;
import cn.liuht.im.client.handler.MessageResponseHandler;
import cn.liuht.im.client.handler.QuitGroupResponseHandler;
import cn.liuht.im.common.codec.PacketDecoder;
import cn.liuht.im.common.codec.PacketEncoder;
import cn.liuht.im.common.codec.Spliter;
import cn.liuht.im.common.util.SessionUtil;
import cn.liuht.im.server.handler.ImIdleStateHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 客户端
 *
 * @author liuht
 * 2019/6/6 15:35
 */
@Slf4j
@Data
public class NettyClient {

    private static Channel channel;

    private static Bootstrap bootstrap;

    public static final int MAX_RETRY = 3;

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 8084;

    private static NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    static {
        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                // 日志级别
                //ch.pipeline().addLast(new LoggingHandler(LogLevel.ERROR));
                // 空闲检测
                ch.pipeline().addLast(new ImIdleStateHandler());
                // 粘包/拆包 编码/解码
                ch.pipeline().addLast(new Spliter());
                ch.pipeline().addLast(new PacketDecoder());
                // 心跳回复
                ch.pipeline().addLast(new HeartBeatResponseHandler());
                // 登录响应处理器
                ch.pipeline().addLast(new LoginResponseHandler());
                // 收消息处理器
                ch.pipeline().addLast(new MessageResponseHandler());
                // 创建群响应处理器
                ch.pipeline().addLast(new CreateGroupResponseHandler());
                // 加群响应处理器
                ch.pipeline().addLast(new JoinGroupResponseHandler());
                // 退群响应处理器
                ch.pipeline().addLast(new QuitGroupResponseHandler());
                // 获取群成员响应处理器
                ch.pipeline().addLast(new ListGroupMembersResponseHandler());
                // 群消息响应
                ch.pipeline().addLast(new GroupMessageResponseHandler());
                // 登出响应处理器
                ch.pipeline().addLast(new LogoutResponseHandler());
                ch.pipeline().addLast(new PacketEncoder());
                // 心跳定时器
                ch.pipeline().addLast(new HeartBeatTimerHandler());
            }
        });
    }

    /**
     * 与服务端建立连接
     *
     * @param maxRetry 最大重试次数
     */
    public static void connect(int maxRetry) {
        if (channel != null && channel.isActive()) {
            return;
        }
        log.info("开始与服务端建立连接");
        bootstrap.connect(HOST, PORT).addListener(future -> {
            if (future.isSuccess()) {
                log.info(new Date() + ": 连接成功,启动控制台线程...");
                channel = ((ChannelFuture) future).channel();
                startConsoleThread();
            } else if (maxRetry == 0) {
                log.error("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - maxRetry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                log.error(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(maxRetry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    /**
     * 从控制台获取消息 并发送给服务端
     */
    private static void startConsoleThread() {
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner scanner = new Scanner(System.in);
        executorService.execute(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    loginConsoleCommand.exec(scanner, channel);
                } else {
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        });
    }
}
