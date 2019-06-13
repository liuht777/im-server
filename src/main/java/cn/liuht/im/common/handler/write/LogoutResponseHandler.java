package cn.liuht.im.common.handler.write;

import cn.liuht.im.common.protocol.response.LogoutResponsePacket;
import cn.liuht.im.common.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述
 *
 * @author liuht
 * 2019/6/13 15:29
 */
@Slf4j
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {
    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final LogoutResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            log.info("成功退出登录");
            SessionUtil.unBindSession(ctx.channel());
        } else {
            log.warn("退出登录失败");
        }
    }
}
