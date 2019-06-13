package cn.liuht.im.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 连接状态监测
 *
 * @author liuht
 * 2019/6/13 17:06
 */
@Slf4j
public class ImIdleStateHandler extends IdleStateHandler {

    private static final int READER_IDLE_TIME = 15;

    /**
     * 如果 15 秒内没有读到数据，就表示连接假死
     */
    public ImIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        log.warn(READER_IDLE_TIME + "秒内未读到数据，关闭连接");
        ctx.channel().close();
    }
}
