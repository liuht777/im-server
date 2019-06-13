package cn.liuht.im.server;

import cn.liuht.im.common.handler.Spliter;
import cn.liuht.im.common.handler.codec.PacketDecoder;
import cn.liuht.im.common.handler.codec.PacketEncoder;
import cn.liuht.im.common.handler.read.AuthHandler;
import cn.liuht.im.common.handler.read.LifeCyCleTestHandler;
import cn.liuht.im.common.handler.read.LoginRequestHandler;
import cn.liuht.im.common.handler.read.MessageRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * Netty Server 监听
 *
 * @author liuht
 * 2019/6/6 14:15
 */
@Component
@Slf4j
public class NettyServerListener {

    /**
     * 创建bootstrap
     */
    ServerBootstrap serverBootstrap = new ServerBootstrap();

    /**
     * BOSS
     */
    EventLoopGroup boss = new NioEventLoopGroup();

    /**
     * Worker
     */
    EventLoopGroup work = new NioEventLoopGroup();

    @Autowired
    private NettyConfig nettyConfig;

    /**
     * 关闭服务器方法
     */
    @PreDestroy
    public void close() {
        log.info("关闭服务器....");
        //优雅退出
        boss.shutdownGracefully();
        work.shutdownGracefully();
    }

    /**
     * 开启及服务线程
     */
    public void start() {
        // 从配置文件中(application.yml)获取服务端监听端口号
        int port = nettyConfig.getPort();
        serverBootstrap.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .handler(new LoggingHandler(LogLevel.INFO));
        //设置事件处理
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new Spliter());
                pipeline.addLast(new LifeCyCleTestHandler());
                pipeline.addLast(new PacketDecoder());
                pipeline.addLast(new LoginRequestHandler());
                pipeline.addLast(new AuthHandler());
                pipeline.addLast(new MessageRequestHandler());
                pipeline.addLast(new PacketEncoder());
            }
        });
        log.info("netty服务器在[{}]端口启动监听", port);
        serverBootstrap.bind(port);
    }
}
