package cn.liuht.im.server.handler;

import cn.liuht.im.common.model.Session;
import cn.liuht.im.common.protocol.request.LogoutRequestPacket;
import cn.liuht.im.common.protocol.response.LogoutResponsePacket;
import cn.liuht.im.common.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 登出请求
 *
 * @author liuht
 * 2019/6/13 15:25
 */
@Slf4j
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    private LogoutRequestHandler() {}

    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final LogoutRequestPacket msg) throws Exception {
        final Session session = SessionUtil.getSession(ctx.channel());
        log.info("用户["+session.getUserName()+"]成功退出登录");
        SessionUtil.unBindSession(ctx.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(logoutResponsePacket);
    }
}
