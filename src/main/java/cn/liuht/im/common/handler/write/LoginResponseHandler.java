package cn.liuht.im.common.handler.write;

import cn.liuht.im.common.protocol.request.LoginRequestPacket;
import cn.liuht.im.common.protocol.response.LoginResponsePacket;
import cn.liuht.im.common.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

/**
 * 登录响应handler
 *
 * @author liuht
 * 2019/6/13 9:25
 */
@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info(new Date() + ": 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("hyman");
        loginRequestPacket.setPassword("pwd123");

        // 写数据
        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final LoginResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            LoginUtil.markAsLogin(ctx.channel());
            log.info(new Date() + ": 客户端登录成功");
        } else {
            log.info(new Date() + ": 客户端登录失败，原因：" + responsePacket.getReason());
        }
    }
}
