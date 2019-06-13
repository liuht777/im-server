package cn.liuht.im.common.handler.response;

import cn.liuht.im.common.model.Session;
import cn.liuht.im.common.protocol.response.GroupMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 16:26
 */
@Slf4j
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {

    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final GroupMessageResponsePacket responsePacket) throws Exception {
        String fromGroupId = responsePacket.getFromGroupId();
        Session fromUser = responsePacket.getFromUser();
        log.info("收到群[" + fromGroupId + "]中[" + fromUser + "]发来的消息：" + responsePacket.getMessage());
    }
}
