package cn.liuht.im.server;

import cn.liuht.im.common.handler.request.*;
import cn.liuht.im.common.protocol.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

import static cn.liuht.im.common.protocol.command.Command.CREATE_GROUP_REQUEST;
import static cn.liuht.im.common.protocol.command.Command.GROUP_MESSAGE_REQUEST;
import static cn.liuht.im.common.protocol.command.Command.JOIN_GROUP_REQUEST;
import static cn.liuht.im.common.protocol.command.Command.LIST_GROUP_MEMBERS_REQUEST;
import static cn.liuht.im.common.protocol.command.Command.LOGOUT_REQUEST;
import static cn.liuht.im.common.protocol.command.Command.MESSAGE_REQUEST;
import static cn.liuht.im.common.protocol.command.Command.QUIT_GROUP_REQUEST;

/**
 * 聊天handler聚合
 *
 * @author liuht
 * 2019/6/13 16:30
 */
@ChannelHandler.Sharable
public class ImHandler extends SimpleChannelInboundHandler<Packet> {

    public static final ImHandler INSTANCE = new ImHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private ImHandler() {
        handlerMap = new HashMap<>();
        handlerMap.put(MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
        handlerMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        handlerMap.put(QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
        handlerMap.put(LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestHandler.INSTANCE);
        handlerMap.put(GROUP_MESSAGE_REQUEST, GroupMessageRequestHandler.INSTANCE);
        handlerMap.put(LOGOUT_REQUEST, LogoutRequestHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final Packet msg) throws Exception {

    }
}
