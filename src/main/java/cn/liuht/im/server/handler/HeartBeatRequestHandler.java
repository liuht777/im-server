package cn.liuht.im.server.handler;

import cn.liuht.im.common.protocol.request.HeartBeatRequestPacket;
import cn.liuht.im.common.protocol.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 心跳请求
 *
 * @author liuht
 * 2019/6/13 17:17
 */
@Slf4j
@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    private HeartBeatRequestHandler() {}

    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final HeartBeatRequestPacket msg) throws Exception {
        log.debug(new Date() + ": 收到心跳["+ctx.channel().id()+"]");
        ctx.writeAndFlush(new HeartBeatResponsePacket());
    }
}
