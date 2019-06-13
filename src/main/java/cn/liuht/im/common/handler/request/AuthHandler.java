package cn.liuht.im.common.handler.request;

import cn.liuht.im.common.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * 登录认证handler
 *
 * @author liuht
 * 2019/6/13 10:20
 */
@Slf4j
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    public static final AuthHandler INSTANCE = new AuthHandler();

    private AuthHandler() {}

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            // 登录认证校验通过过后, 删除这个handler
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }
}
