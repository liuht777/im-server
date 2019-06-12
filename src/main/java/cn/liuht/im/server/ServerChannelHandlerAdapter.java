package cn.liuht.im.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 通道适配器
 *
 * @author liuht
 * 2019/6/6 14:17
 */
@Slf4j
public class ServerChannelHandlerAdapter extends ChannelInboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("服务端收到消息:{}", byteBuf.toString(CharsetUtil.UTF_8));
        ByteBuf out = getByteBuf(ctx);
        log.info("服务端回复消息:{}", out.toString(CharsetUtil.UTF_8));
        ctx.channel().writeAndFlush(out);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "你好，收到! 老表!".getBytes(CharsetUtil.UTF_8);
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(bytes);
        return buffer;
    }
}
