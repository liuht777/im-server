package cn.liuht.im.common.handler.write;

import cn.liuht.im.common.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * MessageResponsePacket handler
 *
 * @author liuht
 * 2019/6/13 9:35
 */
@Slf4j
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final MessageResponsePacket msg) throws Exception {
        log.info(new Date() + ": 收到服务端的消息: " + msg.getMessage());
    }
}
