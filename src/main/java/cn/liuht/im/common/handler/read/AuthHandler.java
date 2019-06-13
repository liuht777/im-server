package cn.liuht.im.common.handler.read;

import cn.liuht.im.common.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 登录认证handler
 *
 * @author liuht
 * 2019/6/13 10:20
 */
@Slf4j
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!LoginUtil.hasLogin(ctx.channel())) {
            log.error(new Date() + ": 无登录验证，强制关闭连接!");
            ctx.channel().close();
        } else {
            // 登录认证校验通过过后, 删除这个handler
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        if (LoginUtil.hasLogin(ctx.channel())) {
            log.info(new Date() + ": 当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
        } else {
            log.error(new Date() + ": 无登录验证，强制关闭连接!");
        }
    }
}
