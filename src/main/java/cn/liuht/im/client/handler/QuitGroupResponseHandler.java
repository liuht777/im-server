package cn.liuht.im.client.handler;

import cn.liuht.im.common.protocol.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 15:58
 */
@Slf4j
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final QuitGroupResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            log.info("退出群聊[" + responsePacket.getGroupId() + "]成功！");
        } else {
            log.error("退出群聊[" + responsePacket.getGroupId() + "]失败！");
        }
    }
}
