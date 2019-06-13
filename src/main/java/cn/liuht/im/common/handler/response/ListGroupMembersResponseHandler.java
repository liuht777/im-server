package cn.liuht.im.common.handler.response;

import cn.liuht.im.common.protocol.response.ListGroupMembersResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 15:57
 */
@Slf4j
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {
    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final ListGroupMembersResponsePacket msg) throws Exception {
        log.info("群[" + msg.getGroupId() + "]中的人包括：" + msg.getSessionList());
    }
}
