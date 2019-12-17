package com.frozens.daily.component.netty;

import com.frozens.daily.component.netty.observable.ConnectObservable;
import com.frozens.daily.component.netty.observable.MessageObservable;
import com.orhanobut.logger.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    private NettyClient nettyClient = null;
    private MessageObservable mMessageObservable = null;
    private ConnectObservable mConnectObservable = null;

    public NettyClientHandler(NettyClient nettyClient) {
        super();
        this.nettyClient = nettyClient;
        this.mConnectObservable = ConnectObservable.getInstance();
        Logger.i("handler");
        this.mMessageObservable = MessageObservable.getInstance();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        Logger.i("read");
        ByteBuf strMsg = (ByteBuf) msg;
        Protocol decode = MessageDecoder.decode(strMsg.copy());
        mMessageObservable.change(decode);
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {


        Logger.i("ClientHandler" + "-------重连回调------");
        mConnectObservable.change(IMConnect.status_reconnect);
        nettyClient.setConnectState(NettyClient.DISCONNECTION);
        nettyClient.connect();
        super.channelInactive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Logger.i("NettyClientHandle" + "registered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        mConnectObservable.change(IMConnect.Status_connect_success);
        Logger.i("=====连接成功回调=====");
        nettyClient.setConnectState(NettyClient.CONNECTED);
        super.channelActive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        mConnectObservable.change(IMConnect.Status_connect_failed);
        Logger.i("NettyClientHandle" + "网络异常!");
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }


}