package cn.liuht.im.common.handler.read;

import cn.liuht.im.common.protocol.request.LoginRequestPacket;
import cn.liuht.im.common.protocol.response.LoginResponsePacket;
import cn.liuht.im.common.util.LoginUtil;
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
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final LoginRequestPacket loginRequestPacket) throws Exception {
        log.info(new Date() + ": 收到登录请求");
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        // 登录校验
        if (valid(loginRequestPacket)) {
            log.info(new Date() + ": 登录成功");
            loginResponsePacket.setSuccess(true);
            // 标记当前的 channel 的状态为已登录
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            log.error(new Date() + ": 登录失败");
            loginResponsePacket.setReason("账号或者密码错误");
            loginResponsePacket.setSuccess(false);
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
}
