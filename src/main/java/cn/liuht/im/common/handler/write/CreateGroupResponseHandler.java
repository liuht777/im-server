package cn.liuht.im.common.handler.write;

import cn.liuht.im.common.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 15:28
 */
@Slf4j
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        log.info("群创建成功，id 为[" + createGroupResponsePacket.getGroupId() + "], ");
        log.info("群里面有：" + createGroupResponsePacket.getUserNameList());
    }
}
