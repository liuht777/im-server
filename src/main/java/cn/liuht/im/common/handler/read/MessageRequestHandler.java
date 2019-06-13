package cn.liuht.im.common.handler.read;

import cn.liuht.im.common.protocol.request.MessageRequestPacket;
import cn.liuht.im.common.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * MessageRequestPacket
 *
 * @author liuht
 * 2019/6/13 9:39
 */
@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final MessageRequestPacket messageRequestPacket) throws Exception {
        log.info(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");

        log.info(new Date() + ": 服务端回复【" + messageRequestPacket.getMessage() + "】");
        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
