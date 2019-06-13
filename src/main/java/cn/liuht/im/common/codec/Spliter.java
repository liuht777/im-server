package cn.liuht.im.common.codec;

import cn.liuht.im.common.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 拆包 粘包 handler
 *
 * @author liuht
 * 2019/6/13 9:48
 */
@Slf4j
public class Spliter extends LengthFieldBasedFrameDecoder {

    private static final int LENGTH_FIELD_OFFSET = 7;
    private static final int LENGTH_FIELD_LENGTH = 4;

    public Spliter() {
        // 第一个参数指的是数据包的最大长度，第二个参数指的是长度域的偏移量，第三个参数指的是长度域的长度
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    /**
     * 第二个参数 in，每次传递进来的时候，均为一个数据包的开头，想了解原理的同学可以参考 https://www.jianshu.com/p/dc26e944da95
     *
     * @param ctx
     * @param in
     * @return
     * @throws Exception
     */
    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 屏蔽非本协议的客户端
        if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
            // 关闭连接
            log.error(new Date() + ": 屏蔽非本协议的客户端,关闭连接");
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }
}
