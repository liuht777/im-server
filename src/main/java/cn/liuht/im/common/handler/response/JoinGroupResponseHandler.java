package cn.liuht.im.common.handler.response;

import cn.liuht.im.common.protocol.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 15:56
 */
@Slf4j
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final JoinGroupResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            log.info("加入群[" + responsePacket.getGroupId() + "]成功!");
        } else {
            log.error("加入群[" + responsePacket.getGroupId() + "]失败，原因为：" + responsePacket.getReason());
        }
    }
}
