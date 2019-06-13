package cn.liuht.im.client.handler;

import cn.liuht.im.common.model.Session;
import cn.liuht.im.common.protocol.response.LoginResponsePacket;
import cn.liuht.im.common.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 登录响应handler
 *
 * @author liuht
 * 2019/6/13 9:25
 */
@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final LoginResponsePacket responsePacket) throws Exception {

        String userId = responsePacket.getUserId();
        String userName = responsePacket.getUserName();

        if (responsePacket.isSuccess()) {
            log.info("[" + userName + "]登录成功，userId 为: " + responsePacket.getUserId());
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
        } else {
            log.error("[" + userName + "]登录失败，原因：" + responsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info(new Date() + ": 客户端连接被关闭!");
    }
}
