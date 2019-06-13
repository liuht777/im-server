package cn.liuht.im.common.codec;

import cn.liuht.im.common.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * byte 转 Packet
 *
 * @author liuht
 * 2019/6/13 9:30
 */
public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(final ChannelHandlerContext ctx, final ByteBuf in, final List<Object> out) throws Exception {
        out.add(PacketCodeC.INSTANCE.decode(in));
    }
}
