package cn.liuht.im.common.handler.response;

import cn.liuht.im.common.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

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
        String fromUserId = msg.getFromUserId();
        String fromUserName = msg.getFromUserName();
        log.info(fromUserId + ":" + fromUserName + " -> " + msg.getMessage());
    }
}
