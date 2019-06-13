package cn.liuht.im.common.codec;

import cn.liuht.im.common.protocol.Packet;
import cn.liuht.im.common.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Packet 转 byte
 *
 * @author liuht
 * 2019/6/13 9:32
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(final ChannelHandlerContext ctx, final Packet packet, final ByteBuf out) throws Exception {
        PacketCodeC.INSTANCE.encode(out, packet);
    }
}
