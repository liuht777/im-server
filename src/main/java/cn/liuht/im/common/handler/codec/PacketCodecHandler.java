package cn.liuht.im.common.handler.codec;

import cn.liuht.im.common.protocol.Packet;
import cn.liuht.im.common.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * 消息包 编码/解码
 *
 * @author liuht
 * 2019/6/13 16:39
 */
@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet> {

    public static final PacketCodecHandler INSTANCE = new PacketCodecHandler();

    private PacketCodecHandler() {}

    @Override
    protected void encode(final ChannelHandlerContext ctx, final Packet packet, final List<Object> out) throws Exception {
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        PacketCodeC.INSTANCE.encode(byteBuf, packet);
        out.add(byteBuf);
    }

    @Override
    protected void decode(final ChannelHandlerContext ctx, final ByteBuf byteBuf, final List<Object> out) throws Exception {
        out.add(PacketCodeC.INSTANCE.decode(byteBuf));
    }
}
