package com.frozen.imsdk;

import android.util.Log;

import com.frozen.daily.imsdk.R;
import com.frozen.imsdk.model.IMConnect;
import com.frozen.imsdk.model.IMMessage;

import java.util.Observable;
import java.util.Observer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundBuffer;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    private NettyClient nettyClient = null;
    private Observable mMessageObservable = null;
    private Observable mConnectObservable = null;

    public NettyClientHandler(NettyClient nettyClient) {
        super();
        this.nettyClient = nettyClient;
        this.mConnectObservable = ConnectObservable.getInstance();
        this.mMessageObservable = ConversationObservable.getInstance();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("read");
        ByteBuf strMsg = (ByteBuf) msg;
        IMMessage decode = MessageDecoder.decode(strMsg.copy());
        mMessageObservable.notifyObservers(decode);
        System.out.println(decode.toString());
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ClientHandler" + "-------重连回调------");
        mConnectObservable.notifyObservers(new IMConnect(IMConnect.CONNECT_SUCCESS));
        nettyClient.setConnectState(NettyClient.DISCONNECTION);
        nettyClient.connect();
        super.channelInactive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("NettyClientHandl" + "registered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        mConnectObservable.notifyObservers(new IMConnect(IMConnect.CONNECT_SUCCESS));
        System.out.println("NettyClientHandler" + "=====连接成功回调=====");
        nettyClient.setConnectState(NettyClient.CONNECTED);
        super.channelActive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        mConnectObservable.notifyObservers(new IMConnect(IMConnect.CONNECT_FAILED));
        System.out.println("NettyClientHandl" + "网络异常!");
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }


}