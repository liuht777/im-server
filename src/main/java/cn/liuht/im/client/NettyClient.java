package cn.liuht.im.client;

import cn.liuht.im.client.command.ConsoleCommandManager;
import cn.liuht.im.client.command.LoginConsoleCommand;
import cn.liuht.im.common.handler.Spliter;
import cn.liuht.im.common.handler.codec.PacketDecoder;
import cn.liuht.im.common.handler.codec.PacketEncoder;
import cn.liuht.im.common.handler.write.CreateGroupResponseHandler;
import cn.liuht.im.common.handler.write.JoinGroupResponseHandler;
import cn.liuht.im.common.handler.write.ListGroupMembersResponseHandler;
import cn.liuht.im.common.handler.write.LoginResponseHandler;
import cn.liuht.im.common.handler.write.LogoutResponseHandler;
import cn.liuht.im.common.handler.write.MessageResponseHandler;
import cn.liuht.im.common.handler.write.QuitGroupResponseHandler;
import cn.liuht.im.common.util.SessionUtil;
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

import java.net.InetSocketAddress;
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

    private Bootstrap bootstrap;

    private ChannelFuture channelFuture;

    private InetSocketAddress address;

    private int retry = 3;

    private NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public NettyClient(InetSocketAddress address) {
        this.address = address;
        init();
        connect(1);
    }

    public NettyClient(String host, Integer port) {
        this(new InetSocketAddress(host, port));
    }

    /**
     * 初始化方法
     */
    private void init() {
        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                ch.pipeline().addLast(new Spliter());
                ch.pipeline().addLast(new PacketDecoder());
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
                // 登出响应处理器
                ch.pipeline().addLast(new LogoutResponseHandler());
                ch.pipeline().addLast(new PacketEncoder());
            }
        });
    }

    /**
     * 与服务端建立连接
     *
     * @param order 第几次简历连接
     */
    private void connect(int order) {
        log.info("开始与服务端建立连接");
        bootstrap.connect(address).addListener(future -> {
            if (future.isSuccess()) {
                log.info("成功与服务端建立连接");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retry == 0) {
                log.error("重试次数已用完，放弃连接！");
            } else {
                // 本次重连的间隔
                int delay = 1 << order;
                retry --;
                log.error(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> {
                    connect(order + 1);
                }, delay, TimeUnit.SECONDS);
            }
        });
    }

    /**
     * 从控制台获取消息 并发送给服务端
     * @param channel
     */
    private void startConsoleThread(Channel channel) {
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
