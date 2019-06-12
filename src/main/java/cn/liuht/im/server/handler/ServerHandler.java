package cn.liuht.im.server.handler;

import cn.liuht.im.common.protocol.Packet;
import cn.liuht.im.common.protocol.PacketCodeC;
import cn.liuht.im.common.protocol.request.LoginRequestPacket;
import cn.liuht.im.common.protocol.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 服务端handler
 *
 * @author liuht
 * 2019/6/12 16:34
 */
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf requestByteBuf = (ByteBuf) msg;
        // 解码
        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);

        // 判断是否是登录请求数据包
        if (packet instanceof LoginRequestPacket) {
            log.info(new Date() + ": 收到登录请求");
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            // 登录校验
            if (valid(loginRequestPacket)) {
                log.info(new Date() + ": 登录成功");
                loginResponsePacket.setSuccess(true);
            } else {
                log.error(new Date() + ": 登录失败");
                loginResponsePacket.setReason("账号或者密码错误");
                loginResponsePacket.setSuccess(false);
            }
            // 编码
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }

    /**
     * 登录校验
     *
     * @param loginRequestPacket
     * @return
     */
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
