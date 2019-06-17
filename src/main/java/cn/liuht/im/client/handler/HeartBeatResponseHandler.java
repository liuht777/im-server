package cn.liuht.im.client.handler;

import cn.liuht.im.common.protocol.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 服务端心跳回复handler
 *
 * @author liuht
 * 2019/6/17 10:48
 */
@Slf4j
public class HeartBeatResponseHandler extends SimpleChannelInboundHandler<HeartBeatResponsePacket> {
    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final HeartBeatResponsePacket msg) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug(new Date() + ": 收到服务端心跳回复...");
        }
    }
}
