package cn.liuht.im.server.handler;

import cn.liuht.im.common.model.Session;
import cn.liuht.im.common.protocol.request.LoginRequestPacket;
import cn.liuht.im.common.protocol.response.LoginResponsePacket;
import cn.liuht.im.common.util.IDUtil;
import cn.liuht.im.common.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * LoginRequestPacket
 *
 * @author liuht
 * 2019/6/13 9:37
 */
@Slf4j
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    private LoginRequestHandler() {}

    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final LoginRequestPacket loginRequestPacket) throws Exception {
        log.info(new Date() + ": 收到登录请求");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUserName());

        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            String userId = IDUtil.randomId();
            loginResponsePacket.setUserId(userId);
            log.info("[" + loginRequestPacket.getUserName() + "]登录成功");
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUserName()), ctx.channel());
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            log.error(new Date() + ": 登录失败");
        }

        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    /**
     * 登录校验
     *
     * @param loginRequestPacket
     * @return
     */
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception{
        Session session = SessionUtil.getSession(ctx.channel());
        if (session != null) {
            log.warn("[" + session.getUserName() + "]下线, 清空缓存");
            SessionUtil.unBindSession(ctx.channel());
        }
        ctx.fireChannelInactive();
    }
}
