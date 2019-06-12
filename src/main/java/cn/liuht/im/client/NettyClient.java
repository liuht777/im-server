package cn.liuht.im.client;

import cn.liuht.im.client.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
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
            protected void initChannel(SocketChannel socketChannel) {
                socketChannel.pipeline().addLast(new ClientHandler());
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

    public static void main(String[] args) {
        new NettyClient("127.0.0.1", 8084);
    }
}
