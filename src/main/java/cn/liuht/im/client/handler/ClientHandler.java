package cn.liuht.im.client.handler;

import cn.liuht.im.common.protocol.Packet;
import cn.liuht.im.common.protocol.PacketCodeC;
import cn.liuht.im.common.protocol.request.LoginRequestPacket;
import cn.liuht.im.common.protocol.response.LoginResponsePacket;
import cn.liuht.im.common.protocol.response.MessageResponsePacket;
import cn.liuht.im.common.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

/**
 * 客户端handler
 *
 * @author liuht
 * 2019/6/12 16:02
 */
@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info(new Date() + ": 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("hyman");
        loginRequestPacket.setPassword("pwd123");

        // 编码
        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

        // 写数据
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            if (loginResponsePacket.isSuccess()) {
                LoginUtil.markAsLogin(ctx.channel());
                log.info(new Date() + ": 客户端登录成功");
            } else {
                log.info(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
        } else if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            log.info(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
        }
    }
}
