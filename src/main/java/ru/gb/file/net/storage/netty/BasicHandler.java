package ru.gb.file.net.storage.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import ru.gb.file.net.storage.netty.dto.AuthRequest;
import ru.gb.file.net.storage.netty.dto.BasicRequest;
import ru.gb.file.net.storage.netty.dto.BasicResponse;
import ru.gb.file.net.storage.netty.dto.GetFileListRequest;

public class BasicHandler extends ChannelInboundHandlerAdapter {

    //private static final Map<Class<? extends BasicRequest>, Consumer<ChannelHandlerContext>> REQUEST_HANDLERS = new HashMap<>();

    //static {
    //    REQUEST_HANDLERS.put(AuthRequest.class, channelHandlerContext -> {
    //        BasicResponse loginOkResponse = new BasicResponse("login ok");
    //        channelHandlerContext.writeAndFlush(loginOkResponse);
    //    });
    //
    //    REQUEST_HANDLERS.put(GetFileListRequest.class, channelHandlerContext -> {
    //        BasicResponse basicResponse = new BasicResponse("file list....");
    //        channelHandlerContext.writeAndFlush(basicResponse);
    //    });
    //}
    private String login;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        BasicRequest request = (BasicRequest) msg;
        System.out.println(request.getType());
        if (request instanceof AuthRequest) {
            BasicResponse loginOkResponse = new BasicResponse("login ok");
            channelHandlerContext.writeAndFlush(loginOkResponse);
        } else if (request instanceof GetFileListRequest) {
            if (login == null) {
                BasicResponse basicResponse = new BasicResponse("Not auth");
                channelHandlerContext.writeAndFlush(basicResponse);
            }
            BasicResponse basicResponse = new BasicResponse("file list....");
            channelHandlerContext.writeAndFlush(basicResponse);
        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

}
