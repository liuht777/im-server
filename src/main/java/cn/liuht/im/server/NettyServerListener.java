package cn.liuht.im.server;

import cn.liuht.im.common.handler.Spliter;
import cn.liuht.im.common.handler.codec.PacketDecoder;
import cn.liuht.im.common.handler.codec.PacketEncoder;
import cn.liuht.im.common.handler.read.AuthHandler;
import cn.liuht.im.common.handler.read.CreateGroupRequestHandler;
import cn.liuht.im.common.handler.read.LoginRequestHandler;
import cn.liuht.im.common.handler.read.LogoutRequestHandler;
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
import java.util.Date;

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
                pipeline.addLast(new PacketDecoder());
                pipeline.addLast(new LoginRequestHandler());
                pipeline.addLast(new AuthHandler());
                pipeline.addLast(new MessageRequestHandler());
                pipeline.addLast(new CreateGroupRequestHandler());
                pipeline.addLast(new LogoutRequestHandler());
                pipeline.addLast(new PacketEncoder());
            }
        });
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                log.info(new Date() + ": 端口[" + port + "]绑定成功!");
            } else {
                log.error("端口[" + port + "]绑定失败!");
            }
        });
    }
}
